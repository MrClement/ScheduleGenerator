package generator;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Excluder {

	public PriorityQueue<CurrentDate> makeExclusionQueue(String filename) {
		PriorityQueue<CurrentDate> temp = new PriorityQueue<CurrentDate>();
		Scanner s;
		s = new Scanner(MultiDayWriter.class.getClassLoader().getResourceAsStream(filename));
		while (s.hasNextLine()) {
			parseDay(s.nextLine(), temp);

		}
		s.close();
		return temp;
	}

	private void parseDay(String line, PriorityQueue<CurrentDate> dates) {
		CurrentDate temp = new CurrentDate();
		String[] brokenLine = line.split(" ");
		temp.setMonth(Integer.parseInt(brokenLine[0]));
		temp.setDay(Integer.parseInt(brokenLine[1]));
		temp.setYear(Integer.parseInt(brokenLine[2]));
		dates.offer(temp);
	}

}
