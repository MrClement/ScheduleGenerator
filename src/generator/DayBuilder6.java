package generator;

import java.util.HashMap;

import sun.net.www.content.image.png;

public class DayBuilder6 extends DayBuilder {
	
	private HashMap<Character, Day> dayMap;

	public DayBuilder6(int lang, int math, int sci, int rot) {
		dayMap = new HashMap<Character, Day>();


	}
	public HashMap<Character, Day> makeNormalDays(int start) {
		dayMap = new HashMap<Character, Day>();
		int temp;
		Period currentPeriod;
		
		// A Day
		char dayType = 'A';
		Day today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.GEOBASEBALL); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT1);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT2);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// B day
		dayType = 'B';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG12);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT3); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT4);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.ELECTIVES);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// C Day
		dayType = 'C';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 90), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT5); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT6);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT7);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// D Day
		dayType = 'D';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), Period.ROT8);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT9);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.HOMEROOM);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.ASSEMBLY); 
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT10);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// E Day
		dayType = 'E';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 5), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), Period.ROT11);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.CLUBS);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.BREAK);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.ROT12); 
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT13);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);


		// F Day
		dayType = 'F';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT14);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 120), Period.LANG12);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.ELECTIVES);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// G Day
		dayType = 'G';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT15);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 120), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT16);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		return dayMap;

	}
	
	////////////////////////////////////////////  WEDNESDAY ////////////////////////////////////////////////////////////////////////////////////////////////////////

	public HashMap<Character, Day> makeWednesdays(int start) {
		dayMap = new HashMap<Character, Day>();
		int temp;
		Period currentPeriod;
		
		// A Day
		char dayType = 'A';
		Day today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 35), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.GEOBASEBALL); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT1);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT2);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// B day
		dayType = 'B';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG12);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 35), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT3); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT4);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 70), Period.ELECTIVES);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// C Day
		dayType = 'C';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 35), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT5); 
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT6);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT7);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// D Day
		dayType = 'D';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 50), Period.ROT8);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT9);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.HOMEROOM);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.ASSEMBLY); 
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT10);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// E Day
		dayType = 'E';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 5), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 55), Period.ROT11);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.CLUBS);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.BREAK);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 30), Period.ROT12); 
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT13);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// F Day
		dayType = 'F';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT14);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 120), Period.LANG12);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG34);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 80), Period.ELECTIVES);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		// G Day
		dayType = 'G';
		today = new Day();
		currentPeriod = new Period(start, temp = makeIntTime(start, 10), Period.HOMEROOM);
		today.add(currentPeriod);
		today.setDayType(dayType);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT15);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 120), Period.LANG34);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 40), Period.LUNCH);
		today.add(currentPeriod);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 125), Period.LANG12);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 60), Period.ROT16);
		today.add(currentPeriod);
		temp = makeIntTime(temp, 5);
		currentPeriod = new Period(temp, temp = makeIntTime(temp, 15), Period.HOMEROOM);
		today.add(currentPeriod);
		dayMap.put(dayType, today);

		return dayMap;

	}

}
