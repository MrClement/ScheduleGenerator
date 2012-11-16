public class ScheduleGeneratorDriver {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(8, 21, 2012);
		CurrentDate endDate = new CurrentDate(5, 24, 2013);
		MultiDayWriter writer = new MultiDayWriter("excluded.txt", startDate, endDate);
		writer.generateICSFile("test.txt");

	}

}
