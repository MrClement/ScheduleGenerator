import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

public class ICSWriter {

	private HashMap<Character, Day> normalDays;
	private HashMap<Character, Day> wednesDays;
	private HashMap<Character, Day> normalDaysDST;
	private HashMap<Character, Day> wednesDaysDST;
	private BufferedWriter out;
	private String filename;

	public ICSWriter() {

		DayBuilder db = new DayBuilder();
		normalDays = db.makeNormalDays(adjustTime(800, new CurrentDate(10, 15, 2012), 1));
		wednesDays = db.makeWednesdays(adjustTime(900, new CurrentDate(10, 15, 2012), 1));
		normalDays = db.makeNormalDays(adjustTime(800, new CurrentDate(11, 5, 2012), 1));
		wednesDays = db.makeWednesdays(adjustTime(900, new CurrentDate(11, 5, 2012), 1));
		for (Entry<Character, Day> e : wednesDays.entrySet()) {
			System.out.println(e.getValue().getDayType());
			System.out.println(e.getValue().toString());
			System.out.println();
		}

	}

	public ICSWriter(String filename) throws IOException {
		DayBuilder db = new DayBuilder();
		normalDays = db.makeNormalDays(adjustTime(800, new CurrentDate(10, 15, 2012), 1));
		wednesDays = db.makeWednesdays(adjustTime(900, new CurrentDate(10, 15, 2012), 1));
		normalDaysDST = db.makeNormalDays(adjustTime(800, new CurrentDate(11, 5, 2012), 1));
		wednesDaysDST = db.makeWednesdays(adjustTime(900, new CurrentDate(11, 5, 2012), 1));
		this.filename = filename;
	}

	public int adjustTime(int time, CurrentDate today, int direction) {
		int currentTime = time;
		if (today.isBefore(new CurrentDate(11, 4, 2012)) || today.isAfterOrEqual(new CurrentDate(3, 10, 2013))) {
			currentTime += 600 * direction;
		} else {
			currentTime += 700 * direction;
		}
		return currentTime;

	}

	public boolean isWednesday(CurrentDate today) {
		Calendar wed = Calendar.getInstance();
		wed.clear();
		wed.set(2012, 1, 4, 8, 8, 8);
		Calendar temp = Calendar.getInstance();
		temp.clear();
		temp.set(today.getYear(), today.getMonth(), today.getDay(), 8, 8, 8);
		while (wed.compareTo(temp) < 0) {
			wed.add(Calendar.DAY_OF_WEEK, 7);
			if (wed.compareTo(temp) == 0) {
				return true;
			}
		}
		return false;

	}

	public void writeHeader() {

	}

	public void writeDayToFile(char dayType, CurrentDate today) throws IOException {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Character, Day> currentDayMap;
		if (today.isBefore(new CurrentDate(11, 4, 2012)) || today.isAfterOrEqual(new CurrentDate(3, 10, 2013))) {
			currentDayMap = isWednesday(today) ? wednesDays : normalDays;
		} else {
			currentDayMap = isWednesday(today) ? wednesDaysDST : normalDaysDST;
		}
		Day dayToPrint = currentDayMap.get(dayType);
		for (Period p : dayToPrint.getD()) {

			Period periodToPrint = p;
			out.write("BEGIN:VEVENT");
			out.newLine();
			out.write("DTSTART:"
					+ today.getYear()
					+ (today.getMonth() > 9 ? today.getMonth() : ("0" + today.getMonth()))
					+ (today.getDay() > 9 ? today.getDay() : ("0" + today.getDay()))
					+ "T"
					+ (periodToPrint.getStartTime() > 1000 ? periodToPrint.getStartTime() : "0"
							+ periodToPrint.getStartTime()) + "Z");
			out.newLine();
			out.write("DTEND:"
					+ today.getYear()
					+ (today.getMonth() > 9 ? today.getMonth() : ("0" + today.getMonth()))
					+ (today.getDay() > 9 ? today.getDay() : ("0" + today.getDay()))
					+ "T"
					+ (periodToPrint.getEndTime() > 1000 ? periodToPrint.getEndTime() : "0"
							+ periodToPrint.getEndTime()) + "Z");
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
			out.write("SUMMARY:Period " + periodToPrint.getNumber());
			out.newLine();
			out.write("TRANSP:OPAQUE");
			out.newLine();
			out.write("END:VEVENT");
			out.newLine();
		}
		out.close();

	}
}
