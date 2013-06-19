package generator;

import java.util.HashMap;

public class DayBuilder6 extends DayBuilder {

	private int langGroup;
	private int mathGroup;
	private int sciGroup;
	private int rotGroup;
	private HashMap<Character, Day> dayMap;

	public DayBuilder6(int lang, int math, int sci, int rot) {
		dayMap = new HashMap<Character, Day>();
		langGroup = lang;
		mathGroup = math;
		sciGroup = sci;
		rotGroup = rot;

	}

	@Override
	public HashMap<Character, Day> makeNormalDays(int start) {
		dayMap = new HashMap<Character, Day>();
		int temp;
		int periodLength = 60;
		Period currentPeriod;
		// A Day
		char dayType = 'A';
		Day today = new Day();

		return null;
	}

	@Override
	public HashMap<Character, Day> makeWednesdays(int start) {

		return null;
	}

}
