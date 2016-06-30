package generator;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by aclement on 6/30/16.
 */
public class PeriodNamer {

    private HashMap<CurrentDate, String> advisorySchedule;

    public PeriodNamer(String advisoryFile) {
        advisorySchedule = new HashMap<>();
        Scanner s;
        s = new Scanner(MultiDayWriter.class.getClassLoader().getResourceAsStream(advisoryFile));
        while (s.hasNextLine()) {
            parseDay(s.nextLine());
        }
        System.out.println(advisorySchedule);
        s.close();
    }

    private void parseDay(String line) {
        CurrentDate temp = new CurrentDate();
        String title = "";
        String[] brokenLine = line.split(" ");
        temp.setMonth(Integer.parseInt(brokenLine[0]));
        temp.setDay(Integer.parseInt(brokenLine[1]));
        temp.setYear(Integer.parseInt(brokenLine[2]));
        String type = brokenLine[3];
        switch (type) {
            case "Classroom":
                title = "Advisory Classroom Meeting";
                break;
            case "Office":
                title = "Advisory Office Hours";
                break;
            case "Lunch":
                title = "Advisory Lunch Meeting";
                break;
            default:
                System.out.println("Error" + line);
                break;
        }
        advisorySchedule.put(temp, title);
    }



    public String accountForCommunityTime(CurrentDate today, Day d) {
        switch (today.getDayOfTheWeek()) {
            case 2:
                return "Assembly";
            case 3:
                return "Advisory";
            case 4:
                return "Class Meeting";
            case 5:
                return "Clubs";
            case 6:
                return "Assembly";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        PeriodNamer c = new PeriodNamer("AdvisorySchedule.txt");
    }
}


