package canvasoutput;

import generator.CurrentDate;
import generator.SchoolType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MultiDayGenerator {

	private PriorityQueue<CurrentDate> daysOff;
	private PriorityQueue<CurrentDate> daysOn;
	private CurrentDate earlyLimit;
	private CurrentDate lateLimit;
	private int startYear;

	public MultiDayGenerator(CurrentDate startDate, CurrentDate endDate, CurrentDate earlyLimit, CurrentDate lateLimit) {
		startYear = startDate.getYear();
		daysOff = new PriorityQueue<CurrentDate>();
		Scanner s;
		s = new Scanner(MultiDayGenerator.class.getClassLoader().getResourceAsStream("excluded.txt"));
		while (s.hasNextLine()) {
			parseDay(s.nextLine());

		}

		s.close();
		makeDaysOn(startDate, endDate);
		this.earlyLimit = earlyLimit;
		this.lateLimit = lateLimit;
	}

	private void makeDaysOn(CurrentDate startDate, CurrentDate endDate) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(startDate.getYear(), startDate.getMonth() - 1, startDate.getDay(), 8, 8, 8);
		Calendar endDay = Calendar.getInstance();
		endDay.clear();
		endDay.set(endDate.getYear(), endDate.getMonth() - 1, endDate.getDay(), 8, 8, 8);
		daysOn = new PriorityQueue<CurrentDate>();
		CurrentDate cd;
		boolean found = false;
		while (cal.compareTo(endDay) < 1) {
			cd = new CurrentDate(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), cal.get(Calendar.YEAR));
			for (CurrentDate c : daysOff) {
				if (c.getMonth() == cd.getMonth() && c.getYear() == cd.getYear() && c.getDay() == cd.getDay()) {
					found = true;
				}
			}
			if (!found && !isWeekend(cd)) {
				daysOn.offer(cd);
			}
			found = false;
			cal.add(Calendar.DATE, 1);
		}

	}

	public boolean isWeekend(CurrentDate today) {
		Calendar sat = Calendar.getInstance();
		sat.clear();
		sat.set(startYear, 0, 1, 8, 8, 8);
		while (sat.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			sat.add(Calendar.DATE, 1);
		}
		Calendar sun = Calendar.getInstance();
		sun.clear();
		sun.set(startYear, 0, sat.get(Calendar.DATE) + 1, 8, 8, 8);
		Calendar temp = Calendar.getInstance();
		temp.clear();
		temp.set(today.getYear(), today.getMonth() - 1, today.getDay(), 8, 8, 8);
		while (sat.compareTo(temp) < 0 || sun.compareTo(temp) < 0) {
			sat.add(Calendar.DAY_OF_WEEK, 7);
			sun.add(Calendar.DAY_OF_WEEK, 7);
			if (sat.compareTo(temp) == 0 || sun.compareTo(temp) == 0) {
				return true;
			}
		}
		return false;

	}

	public ArrayList<Event> generateEvents(int[] periodsToInclude,
			String[] periodNames,
			SchoolType s,
			HashMap<Character, Integer> singleBox,
			boolean includeBreaksAndLunch,
			boolean midSchoolElective,
			boolean sixth,
			int[] sixthPrefs) {
		EventGenerator e;
		char dayType = 'A';
		int dayAdjust = 0;
		ArrayList<Event> allEvents = new ArrayList<Event>();
		try {
			e = new EventGenerator(s);
			for (CurrentDate c : daysOn) {
				char currentDayType = (char) (dayType + dayAdjust);
				if (c.isAfterOrEqual(earlyLimit) && (c.isBefore(lateLimit) || c.equals(lateLimit))) {
					allEvents.addAll(e.generateDay(currentDayType, c, periodsToInclude, periodNames, singleBox,
							sixthPrefs, includeBreaksAndLunch, midSchoolElective, sixth));
				}
				dayAdjust = (dayAdjust + 1) % 7;

			}
			return allEvents;

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return null;
		}
	}

	private void parseDay(String line) {
		CurrentDate temp = new CurrentDate();
		String[] brokenLine = line.split(" ");
		temp.setMonth(Integer.parseInt(brokenLine[0]));
		temp.setDay(Integer.parseInt(brokenLine[1]));
		temp.setYear(Integer.parseInt(brokenLine[2]));
		daysOff.offer(temp);
	}

	public PriorityQueue<CurrentDate> getDaysOn() {
		return daysOn;
	}

}
