package generator;

import java.io.IOException;

//import java.io.IOException;

public class CalendarTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ICSWriter test = new ICSWriter("Hi.txt", SchoolType.HIGH);

		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrentDate test = new CurrentDate(8, 22, 2013);
		test.add(1);
		System.out.println(test);
		System.out.println(test.getDayOfTheWeek());
		test.add(11);
		System.out.println(test);
		System.out.println(test.getDayOfTheWeek());
		test.add(-1);
		System.out.println(test);
		System.out.println(test.getDayOfTheWeek());
		test.add(-1);
		System.out.println(test);
		System.out.println(test.getDayOfTheWeek());

	}
}
