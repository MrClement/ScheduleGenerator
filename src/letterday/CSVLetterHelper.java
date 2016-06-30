package letterday;

import generator.CurrentDate;
import generator.MultiDayWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class CSVLetterHelper {

	private MultiDayWriter writer;
	private BufferedWriter out;

    CSVLetterHelper(String daysOffFile, CurrentDate startDate, CurrentDate endDate, CurrentDate earlyLimit,
			CurrentDate lateLimit, String filename) {
		writer = new MultiDayWriter(daysOffFile, startDate, endDate, earlyLimit, lateLimit);
		PriorityQueue<CurrentDate> daysOn = writer.getDaysOn();
		try {
			File old = new File(filename);
			old.delete();
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write("Date, Day Type");
			out.newLine();
			char dayType = 'A';
			int dayAdjust = 0;
			for (CurrentDate currentDate : daysOn) {
				char currentDayType = (char) (dayType + dayAdjust);
				if(currentDayType == 'B' || currentDayType == 'F') {
					out.write(currentDate + ",  " + currentDayType);
					out.newLine();
				}
				dayAdjust = (dayAdjust + 1) % 7;

			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
