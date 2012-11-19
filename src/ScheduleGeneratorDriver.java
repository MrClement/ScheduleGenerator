public class ScheduleGeneratorDriver {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(8, 21, 2012);
		CurrentDate endDate = new CurrentDate(5, 24, 2013);
		MultiDayWriter writer = new MultiDayWriter("excluded.txt", startDate, endDate);
		int[] temp = {1, 2, 3, 4, 5, 6, 7};
		String[] periodNames = {"Period 1", "Period 2", "Period 3", "AI", "Period 5", "Period 6", "Period 7"};
		writer.generateICSFile("test.txt", temp, periodNames);

	}

}
