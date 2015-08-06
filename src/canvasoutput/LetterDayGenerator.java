package canvasoutput;

import generator.CurrentDate;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class LetterDayGenerator {

	private MultiDayGenerator generator;
	private ArrayList<Event> days;

	public LetterDayGenerator(CurrentDate startDate, CurrentDate endDate, CurrentDate earlyLimit, CurrentDate lateLimit) {
		generator = new MultiDayGenerator(startDate, endDate, earlyLimit, lateLimit);
		PriorityQueue<CurrentDate> daysOn = generator.getDaysOn();
		days = new ArrayList<Event>();
		char dayType = 'A';
		int dayAdjust = 0;
		for (CurrentDate currentDate : daysOn) {
			char currentDayType = (char) (dayType + dayAdjust);
			generateDay(currentDayType, currentDate);
			dayAdjust = (dayAdjust + 1) % 7;
		}

	}

	private void generateDay(char dayType, CurrentDate today) {
		Event e = new Event(today);
		e.setDescription(dayType + " Day");
		days.add(e);

	}

	public ArrayList<Event> getDays() {
		return days;
	}

}
