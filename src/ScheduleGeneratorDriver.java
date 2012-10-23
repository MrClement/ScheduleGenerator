public class ScheduleGeneratorDriver {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(10, 22, 2012);
		CurrentDate endDate = new CurrentDate(10, 26, 2012);
		MultiDayWriter writer = new MultiDayWriter("excluded.txt", startDate, endDate);
		writer.generateICSFile("test.txt");

	}

}
