package letterday;

import generator.CurrentDate;
import generator.MultiDayWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.PriorityQueue;

public class ICSLetterHelper {

	private MultiDayWriter writer;
	private String filename;
	private BufferedWriter out;

	public ICSLetterHelper(String daysOffFile, CurrentDate startDate, CurrentDate endDate, CurrentDate earlyLimit,
			CurrentDate lateLimit, String filename) {
		writer = new MultiDayWriter(daysOffFile, startDate, endDate, earlyLimit, lateLimit);
		this.filename = filename;
		PriorityQueue<CurrentDate> daysOn = writer.getDaysOn();
		char dayType = 'A';
		int dayAdjust = 0;
		writeHeader();
		for (CurrentDate currentDate : daysOn) {
			System.out.println(currentDate);
			char currentDayType = (char) (dayType + dayAdjust);
			try {
				writeDay(currentDayType, currentDate);
				dayAdjust = (dayAdjust + 1) % 7;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		writeFooter();
	}

	private void writeHeader() {
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

	private void writeFooter() {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write("END:VCALENDAR");
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeDay(char dayType, CurrentDate today) throws IOException {
		CurrentDate tomorrow = getTomorrow(today);
		out = new BufferedWriter(new FileWriter(filename, true));
		out.write("BEGIN:VEVENT");
		out.newLine();
		out.write("DTSTART;VALUE=DATE:" + today.getYear()
				+ (today.getMonth() > 9 ? today.getMonth() : ("0" + today.getMonth()))
				+ (today.getDay() > 9 ? today.getDay() : ("0" + today.getDay())));
		out.newLine();
		out.write("DTEND;VALUE=DATE:" + tomorrow.getYear()
				+ (tomorrow.getMonth() > 9 ? tomorrow.getMonth() : ("0" + tomorrow.getMonth()))
				+ (tomorrow.getDay() > 9 ? tomorrow.getDay() : ("0" + tomorrow.getDay())));
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
		out.write("SUMMARY:" + dayType + " Day");
		out.newLine();
		out.write("TRANSP:OPAQUE");
		out.newLine();
		out.write("END:VEVENT");
		out.newLine();
		out.close();
	}

	private CurrentDate getTomorrow(CurrentDate today) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(today.getYear(), today.getMonth() - 1, today.getDay());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		CurrentDate t1 = new CurrentDate(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.YEAR));
		return t1;
	}

}
