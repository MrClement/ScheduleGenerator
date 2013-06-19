package generator;

import java.util.HashMap;

public class DayBuilder {

	private HashMap<Character, Day> dayMap;

	public DayBuilder() {
		dayMap = new HashMap<Character, Day>();
	}

	public HashMap<Character, Day> makeNormalDays(int start, CurrentDate schoolStartDate) {
		CurrentDate now = schoolStartDate;
		dayMap = new HashMap<Character, Day>();
		int temp;
		for (int i = 0; i < 7; i++) {
			char dayType = 'A';
			dayType += i;
			Day today = new Day();
			if (dayType != 'B' && dayType != 'E') {
				Period currentPeriod = new Period(start, temp = makeIntTime(start, 60), (dayType % 65) % 7 + 1);
				today.add(currentPeriod);
				today.setDayType(dayType);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 2) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 10), -1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 20), studentLife(now));
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 75), (dayType % 65 + 3) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), -7);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 5) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 6) % 7 + 1);
				today.add(currentPeriod);
				dayMap.put(dayType, today);
			} else {
				Period currentPeriod = new Period(start, temp = makeIntTime(start, 60), (dayType % 65) % 7 + 1);
				today.add(currentPeriod);
				today.setDayType(dayType);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 2) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 10), -1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 75), (dayType % 65 + 3) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 20), studentLife(now));
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), -7);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 5) % 7 + 1);
				today.add(currentPeriod);
				temp = makeIntTime(temp, 5);
				currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), (dayType % 65 + 6) % 7 + 1);
				today.add(currentPeriod);
				dayMap.put(dayType, today);
			}
			now.add(1);
		}
		return dayMap;

	}

	public HashMap<Character, Day> makeWednesdays(int start, CurrentDate dayAfterDSTStartDate) {
		dayMap = new HashMap<Character, Day>();
		int temp;
		for (int i = 0; i < 7; i++) {
			char dayType = 'A';
			Day today = new Day();
			dayType += i;
			Period currentPeriod = new Period(start, temp = makeIntTime(start, 55), (dayType % 65) % 7 + 1);
			today.add(currentPeriod);
			today.setDayType(dayType);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), (dayType % 65 + 2) % 7 + 1);
			today.add(currentPeriod);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), -3); //Class meeting
			today.add(currentPeriod);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 70), (dayType % 65 + 3) % 7 + 1);
			today.add(currentPeriod);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 45), -7);
			today.add(currentPeriod);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), (dayType % 65 + 5) % 7 + 1);
			today.add(currentPeriod);
			temp = makeIntTime(temp, 5);
			currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), (dayType % 65 + 6) % 7 + 1);
			today.add(currentPeriod);
			dayMap.put(dayType, today);
		}
		return dayMap;

	}

	private int makeIntTime(int time, int add) {
		int temp = (time % 1000) % 100;
		time -= temp;
		time += ((temp + add) / 60) * 100;
		time += ((temp + add) % 60);
		return time;
	}

	private int studentLife(CurrentDate today) {

		switch (today.getDayOfTheWeek()) {
			case 1:
				return -4;
			case 2:
				return -2;
			case 3:
				return -3;
			case 4:
				return -5;
			case 5:
				return -2;
			default:
				return -8;
		}
	}
	

}
