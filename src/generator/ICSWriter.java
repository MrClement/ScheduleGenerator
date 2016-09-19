package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ICSWriter {

	private HashMap<Character, Day> normalDays;
	private HashMap<Character, Day> wednesDays;
	private HashMap<Character, Day> normalDaysDST;
	private HashMap<Character, Day> wednesDaysDST;
	private PriorityQueue<CurrentDate> excludedElectives;
	private PriorityQueue<CurrentDate> excludedSevenEight;
	private PriorityQueue<CurrentDate> excludedSixth;
	private BufferedWriter out;
	private String filename;
	private int startYear = Dates.START_YEAR;
	private CurrentDate schoolStartDate = new CurrentDate(Dates.SCHOOL_START_MONTH, Dates.SCHOOL_START_DAY,
			Dates.SCHOOL_START_YEAR);
	private CurrentDate dstStartDate = new CurrentDate(Dates.DST_START_MONTH, Dates.DST_START_DAY, Dates.DST_START_YEAR);
	private CurrentDate dstEndDate = new CurrentDate(Dates.DST_END_MONTH, Dates.DST_END_DAY, Dates.DST_END_YEAR);
	private CurrentDate dayAfterDSTStartDate = new CurrentDate(Dates.DST_START_MONTH, Dates.DST_START_DAY + 1,
			Dates.DST_START_YEAR);
	private SchoolType school;
    private PeriodNamer namer;


	public ICSWriter(String filename, SchoolType s) throws IOException {
		this.school = s;
		DayBuilder db = null;
		switch (s) {
			case SIXTH:
				db = new DayBuilder6();
				break;
			case SEVENEIGHT:
				db = new DayBuilder78();
				break;
			case HIGH:
				db = new DayBuilder();
				break;
			default:
				break;
		}

		normalDays = db.makeNormalDays(adjustTime(800, schoolStartDate, 1));
		wednesDays = db.makeWednesdays(adjustTime(900, schoolStartDate, 1));
		normalDaysDST = db.makeNormalDays(adjustTime(800, dayAfterDSTStartDate, 1));
		wednesDaysDST = db.makeWednesdays(adjustTime(900, dayAfterDSTStartDate, 1));

		Excluder e = new Excluder();
		excludedElectives = e.makeExclusionQueue("ExcludedElectives.txt");
		excludedSevenEight = e.makeExclusionQueue("ExcludedSevenEight.txt");
		excludedSixth = e.makeExclusionQueue("ExcludedSixth.txt");

        namer = new PeriodNamer("AdvisorySchedule.txt");

		this.filename = filename;
	}

	public int adjustTime(int time, CurrentDate today, int direction) {
		int currentTime = time;
		if (today.isBefore(dstStartDate) || today.isAfterOrEqual(dstEndDate)) {
			currentTime += 600 * direction;
		} else {
			currentTime += 700 * direction;
		}
		return currentTime;

	}

	public boolean isWednesday(CurrentDate today) {
		Calendar wed = Calendar.getInstance();
		wed.clear();
		wed.set(startYear, 0, 1, 8, 8, 8);
		while (wed.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
			wed.add(Calendar.DATE, 1);
		}
		Calendar temp = Calendar.getInstance();
		temp.clear();
		temp.set(today.getYear(), today.getMonth() - 1, today.getDay(), 8, 8, 8);
		while (wed.compareTo(temp) < 0) {
			wed.add(Calendar.DAY_OF_WEEK, 7);
			if (wed.compareTo(temp) == 0) {
				return true;
			}
		}
		return false;

	}

	public void writeHeader() {
		File old = new File(filename);
		old.delete();
		try {
			out = new BufferedWriter(new FileWriter(filename, true));

			out.write("BEGIN:VCALENDAR");
			out.newLine();
			out.write("PRODID:-//Google Inc//Google Calendar 70.9054//EN");
			out.newLine();
			out.write("VERSION:2.0");
			out.newLine();
			out.write("CALSCALE:GREGORIAN");
			out.newLine();
			out.write("METHOD:PUBLISH");
			out.newLine();
			out.write("X-WR-CALNAME:All Periods");
			out.newLine();
			out.write("X-WR-TIMEZONE:America/Denver");
			out.newLine();
			out.write("X-WR-CALDESC:");
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeFooter() {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write("END:VCALENDAR");
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void writeDayToFile(char dayType,
			CurrentDate today,
			int[] periodsToInclude,
			String[] periodNames,
			HashMap<Character, Integer> singleBox,
			int[] sixthPrefs,
			boolean includeBreaksAndLunch,
			boolean midSchoolElective,
			boolean sixth) throws IOException {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HashMap<Character, Day> currentDayMap;
		if (today.isBefore(dstStartDate) || today.isAfterOrEqual(dstEndDate)) {
			currentDayMap = isWednesday(today) ? wednesDays : normalDays;
		} else {
			currentDayMap = isWednesday(today) ? wednesDaysDST : normalDaysDST;
		}
		Day dayToPrint = currentDayMap.get(dayType);
		for (Period p : dayToPrint.getD()) {
            boolean skipBreak = false;
            boolean changeTime = false;
			if(dayType != 'B' && dayType != 'E' && today.getDayOfTheWeek() == 3) {
				if(p.getNumber() == Period.BREAK) {
                    skipBreak = true;
				} else if(p.getNumber() == Period.COMMUNITYTIME){
					p.setStartTime(p.getStartTime() - 15);
                    changeTime = true;
				}
			}
			if (!skipBreak && periodShouldBeIncluded(p.getNumber(), periodsToInclude, includeBreaksAndLunch, midSchoolElective, today)) {
				if (singleBox.size() < 1 || (singleBox.containsKey(dayType) && p.getNumber() == singleBox.get(dayType))) {
					Period periodToPrint = p;
					out.write("BEGIN:VEVENT");
					out.newLine();
					out.write("DTSTART:"
							+ today.getYear()
							+ (today.getMonth() > 9 ? today.getMonth() : ("0" + today.getMonth()))
							+ (today.getDay() > 9 ? today.getDay() : ("0" + today.getDay()))
							+ "T"
							+ (periodToPrint.getStartTime() > 1000 ? periodToPrint.getStartTime() : "0"
									+ periodToPrint.getStartTime()) + "00Z");
					out.newLine();
					out.write("DTEND:"
							+ today.getYear()
							+ (today.getMonth() > 9 ? today.getMonth() : ("0" + today.getMonth()))
							+ (today.getDay() > 9 ? today.getDay() : ("0" + today.getDay()))
							+ "T"
							+ (periodToPrint.getEndTime() > 1000 ? periodToPrint.getEndTime() : "0"
									+ periodToPrint.getEndTime()) + "00Z");
					out.newLine();
					out.write("DTSTAMP:20120820T172628Z");
					out.newLine();
					out.write("UID:");
					out.newLine();
					out.write("CLASS:PUBLIC");
					out.newLine();
					out.write("CREATED:19000101T120000Z");
					out.newLine();
					out.write("DESCRIPTION:");
					out.newLine();
					out.write("LAST-MODIFIED:20120820T172422Z");
					out.newLine();
					out.write("LOCATION:");
					out.newLine();
					out.write("SEQUENCE:0");
					out.newLine();
					out.write("STATUS:CONFIRMED");
					out.newLine();
					out.write("SUMMARY:" + namer.periodName(periodToPrint.getNumber(), periodNames, today, sixth, sixthPrefs));
					out.newLine();
					out.write("TRANSP:OPAQUE");
					out.newLine();
					out.write("END:VEVENT");
					out.newLine();
				}
			}
			if(changeTime) {
                p.setStartTime(p.getStartTime() + 15);
            }
		}
		out.close();

	}

	private boolean periodShouldBeIncluded(int periodNumber,
			int[] periodsToInclude,
			boolean includeBreaksAndLunch,
			boolean midSchoolElective,
			CurrentDate today) {
		boolean found = false;
		boolean electiveInclusion = true;
		for (int i = 0; i < periodsToInclude.length; i++) {
			if (found = periodsToInclude[i] == periodNumber)
				break;
		}
		for (CurrentDate c : excludedElectives) {
			if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear() && c.getDay() == today.getDay()) {
				electiveInclusion = false;
			}
		}
		switch (school) {
			case SEVENEIGHT:
				for (CurrentDate c : excludedSevenEight) {
					if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear()
							&& c.getDay() == today.getDay()) {
						return false;
					}
				}
				break;
			case SIXTH:
				for (CurrentDate c : excludedSixth) {
					if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear()
							&& c.getDay() == today.getDay()) {
						return false;
					}
				}
				break;
			default:
				break;
		}
		return (((periodNumber < 0 && periodNumber > -15) && includeBreaksAndLunch) || (periodNumber == Period.ELECTIVES
				&& midSchoolElective && electiveInclusion))
				|| found;
	}


}
