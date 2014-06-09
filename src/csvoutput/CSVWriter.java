package csvoutput;

import generator.CurrentDate;
import generator.Day;
import generator.DayBuilder;
import generator.DayBuilder78;
import generator.Period;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

public class CSVWriter {

	private HashMap<Character, Day> normalDays;
	private HashMap<Character, Day> wednesDays;
	private HashMap<Character, Day> normalDaysDST;
	private HashMap<Character, Day> wednesDaysDST;
	private BufferedWriter out;
	private String filename;
	private int startYear = 2012;
	private CurrentDate schoolStartDate = new CurrentDate(8, 20, 2014);
	private CurrentDate dstStartDate = new CurrentDate(11, 2, 2014);
	private CurrentDate dstEndDate = new CurrentDate(3, 8, 2015);
	private CurrentDate dayAfterDSTStartDate = new CurrentDate(11, 3, 2014);

	public CSVWriter(boolean schoolType) {

		DayBuilder db = new DayBuilder();
		normalDays = db.makeNormalDays(adjustTime(800, schoolStartDate, 1));
		wednesDays = db.makeWednesdays(adjustTime(900, schoolStartDate, 1));
		normalDaysDST = db.makeNormalDays(adjustTime(800, dayAfterDSTStartDate, 1));
		wednesDaysDST = db.makeWednesdays(adjustTime(900, dayAfterDSTStartDate, 1));
		for (Entry<Character, Day> e : wednesDays.entrySet()) {
			System.out.println(e.getValue().getDayType());
			System.out.println(e.getValue().toString());
			System.out.println();
		}

	}

	public CSVWriter(String filename, boolean schoolType) throws IOException {
		DayBuilder db = schoolType ? new DayBuilder() : new DayBuilder78();
		normalDays = db.makeNormalDays(adjustTime(800, schoolStartDate, 1));
		wednesDays = db.makeWednesdays(adjustTime(900, schoolStartDate, 1));
		normalDaysDST = db.makeNormalDays(adjustTime(800, dayAfterDSTStartDate, 1));
		wednesDaysDST = db.makeWednesdays(adjustTime(900, dayAfterDSTStartDate, 1));
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
			out.write("Day Type, Period 1, Period 2, Period 3, Period 4, Period 5, Period 6, Period 7, Period 8");
			out.newLine();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String periodName(int periodNumber, CurrentDate today) {
		String s = "";
		switch (periodNumber) {
			case Period.BREAK:
				s = "Break";
				break;
			case Period.ASSEMBLY:
				s = "Assembly";
				break;
			case Period.CLASSMEETING:
				s = "Class Meeting";
				break;
			case Period.ADVISORY:
				s = "Advisory";
				break;
			case Period.CLUBS:
				s = "Clubs";
				break;
			case Period.LUNCH:
				s = "Lunch";
				break;
			case Period.SPORTS:
				s = "Sports";
				break;
			case Period.STUDYHALL:
				s = "Study Hall";
				break;
			case Period.ELECTIVES:
				s = "Electives";
				break;
			case Period.COMMUNITYTIME:
				s = communityTime(today);
				break;
			default:
				s = "Period " + periodNumber;
				break;
		}
		return s;
	}

	private String communityTime(CurrentDate today) {
		System.out.println(today.toString() + today.getDayOfTheWeek());
		switch (today.getDayOfTheWeek()) {
			case 2:
				return "Advisory";
			case 3:
				return "Assembly";
			case 4:
				return "Class Meeting";
			case 5:
				return "Clubs";
			case 6:
				return "Assembly";
			default:
				return "";
		}
	}

	public void writeDayToFile(char dayType, CurrentDate today) throws IOException {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Character, Day> currentDayMap;
		if (today.isBefore(dstStartDate) || today.isAfterOrEqual(dstEndDate)) {
			currentDayMap = isWednesday(today) ? wednesDays : normalDays;
		} else {
			currentDayMap = isWednesday(today) ? wednesDaysDST : normalDaysDST;

		}

		Day dayToPrint = currentDayMap.get(dayType);

		// Code for dayType

		// out.write(today.toString() + ", " + dayType);
		// out.newLine();

		// Code for colab tool

		for (int roomNumber = 1; roomNumber < 12; roomNumber++) {
			out.write(today.toString() + ", " + "0" + ", "
					+ (adjustTime(dayToPrint.getD().peek().getStartTime(), today, -1) - 100) + ", "
					+ adjustTime(dayToPrint.getD().peek().getStartTime(), today, -1) + ", " + roomNumber + ",,"
					+ "FALSE");
			out.newLine();
			out.write(today.toString() + ", " + "8" + ", " + "1530" + ", " + "1630" + ", " + roomNumber + ",,"
					+ "FALSE");
			out.newLine();
			out.write(today.toString() + ", " + "9" + ", " + "1630" + ", " + "1730" + ", " + roomNumber + ",,"
					+ "FALSE");
			out.newLine();
		}
		for (Period p : dayToPrint.getD()) {
			if (p.getNumber() > 0) {
				Period periodToPrint = p;
				for (int roomNumber = 1; roomNumber < 12; roomNumber++) {
					out.write(today.toString() + ", " + periodToPrint.getNumber() + ", "
							+ adjustTime(periodToPrint.getStartTime(), today, -1) + ", "
							+ adjustTime(periodToPrint.getEndTime(), today, -1) + ", " + roomNumber + ",," + "FALSE");
					// out.write(periodName(periodToPrint.getNumber(), today) +
					// " - "
					// + adjustTime(periodToPrint.getStartTime(), today, -1) +
					// " - "
					// + adjustTime(periodToPrint.getEndTime(), today, -1) +
					// ", ");

					out.newLine();
				}

			} else if (p.getNumber() == Period.LUNCH) {
				Period periodToPrint = p;
				for (int roomNumber = 1; roomNumber < 12; roomNumber++) {
					int start = adjustTime(periodToPrint.getStartTime(), today, -1);
					int end = adjustTime(periodToPrint.getEndTime(), today, -1);
					int mid = getMid(start, end);
					out.write(today.toString() + ", " + 10 + ", " + start + ", " + mid + ", " + roomNumber + ",,"
							+ "FALSE");
					out.newLine();
					out.write(today.toString() + ", " + 11 + ", " + mid + ", " + end + ", " + roomNumber + ",,"
							+ "FALSE");

					out.newLine();
				}
			}

		}
		out.close();
	}

	private int getMid(int start, int end) {
		int mid;
		if (end / 100 - start / 100 < 1) {
			mid = sanitizeTime(start + (end - start) / 2);
		} else {
			mid = start + ((((start / 100) * 100 + 60) - start) + (end % 100)) / 2;
		}
		mid = sanitizeTime(mid);
		return mid;
	}

	private int sanitizeTime(int time) {
		int timeOver = (time % 100);
		if (timeOver / 60 >= 1) {
			return (time / 100) * 100 + 100 + timeOver % 60;
		} else
			return time;
	}

}
