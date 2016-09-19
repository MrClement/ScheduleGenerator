package generator;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Handles periods based on student preferences and advisory schedule
 *
 */
public class PeriodNamer {

    private HashMap<String, String> advisorySchedule;

    public PeriodNamer(String advisoryFile) {
        advisorySchedule = new HashMap<String, String>();
        Scanner s;
        s = new Scanner(MultiDayWriter.class.getClassLoader().getResourceAsStream(advisoryFile));
        while (s.hasNextLine()) {
            parseDay(s.nextLine());
        }
        s.close();
    }

    public String advisoryType(CurrentDate today) {
        return advisorySchedule.get(today.toString());
    }

    public String periodName(int periodNumber, String[] periodNames, CurrentDate today, boolean sixth, int[] sixthPrefs) {
        if (sixth) {
            return periodNameSixth(periodNumber, sixthPrefs);
        } else {
            return periodNameNonSixth(periodNumber, periodNames, today);
        }
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
        advisorySchedule.put(temp.toString(), title);
    }



    private String periodNameSixth(int periodNumber, int[] sixthPrefs) {
        String s = "";
        switch (periodNumber) {
            case Period.HOMEROOM:
                s = "Homeroom";
                break;
            case Period.GEOBASEBALL:
                s = "Geobaseball";
                break;
            case Period.ELECTIVES:
                s = "Electives";
                break;
            case Period.LUNCH:
                s = "Lunch";
                break;
            case Period.ASSEMBLY:
                s = "Assembly";
                break;
            case Period.BREAK:
                s = "Break";
                break;
            case Period.CLUBS:
                s = "Clubs";
                break;
            case Period.LANG12:
                switch (sixthPrefs[0]) {
                    case 1:
                    case 2:
                        s = "Language Arts";
                        break;
                    case 3:
                    case 4:
                        s = "Math";
                        break;

                    default:
                        break;
                }
                break;
            case Period.SS12:
                switch (sixthPrefs[0]) {
                    case 1:
                    case 2:
                        s = "Social Studies";
                        break;
                    case 3:
                    case 4:
                        s = "Science";
                        break;

                    default:
                        break;
                }
                break;
            case Period.LANG34:
                switch (sixthPrefs[0]) {
                    case 1:
                    case 2:
                        s = "Math";
                        break;
                    case 3:
                    case 4:
                        s = "Language Arts";
                        break;

                    default:
                        break;
                }
                break;
            case Period.SS34:
                switch (sixthPrefs[0]) {
                    case 1:
                    case 2:
                        s = "Science";
                        break;
                    case 3:
                    case 4:
                        s = "Social Studies";
                        break;

                    default:
                        break;
                }
                break;
            case Period.ROT1:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "PE";
                        break;
                    case 2:
                        s = "Latin";
                        break;
                    case 3:
                        s = "Art";
                        break;
                    case 4:
                        s = "Writing";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT2:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Art";
                        break;
                    case 2:
                        s = "PE";
                        break;
                    case 3:
                        s = "Latin";
                        break;
                    case 4:
                        s = "Study Hall";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT3:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "PE";
                        break;
                    case 2:
                        s = "PE";
                        break;
                    case 3:
                        s = "Writing";
                        break;
                    case 4:
                        s = "Art";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT4:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Writing";
                        break;
                    case 2:
                        s = "Art";
                        break;
                    case 3:
                        s = "PE";
                        break;
                    case 4:
                        s = "Latin";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT5:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Art";
                        break;
                    case 2:
                        s = "Writing";
                        break;
                    case 3:
                        s = "PE";
                        break;
                    case 4:
                        s = "PE";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT6:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Latin";
                        break;
                    case 2:
                        s = "PE";
                        break;
                    case 3:
                        s = "Art";
                        break;
                    case 4:
                        s = "Writing";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT7:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "PE";
                        break;
                    case 2:
                        s = "Latin";
                        break;
                    case 3:
                        s = "Study Hall";
                        break;
                    case 4:
                        s = "Art";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT8:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Writing";
                        break;
                    case 2:
                        s = "Art";
                        break;
                    case 3:
                        s = "Latin";
                        break;
                    case 4:
                        s = "Study Hall";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT9:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Study Hall";
                        break;
                    case 2:
                        s = "Writing";
                        break;
                    case 3:
                        s = "PE";
                        break;
                    case 4:
                        s = "Art";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT10:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Latin";
                        break;
                    case 2:
                        s = "Study Hall";
                        break;
                    case 3:
                        s = "Art";
                        break;
                    case 4:
                        s = "PE";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT11:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "PE";
                        break;
                    case 2:
                        s = "Latin";
                        break;
                    case 3:
                        s = "Art";
                        break;
                    case 4:
                        s = "Writing";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT12:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Art";
                        break;
                    case 2:
                        s = "PE";
                        break;
                    case 3:
                        s = "Writing";
                        break;
                    case 4:
                        s = "Latin";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT13:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Writing";
                        break;
                    case 2:
                        s = "Art";
                        break;
                    case 3:
                        s = "Latin";
                        break;
                    case 4:
                        s = "PE";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT14:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Latin";
                        break;
                    case 2:
                        s = "WTRG";
                        break;
                    case 3:
                        s = "Study Hall";
                        break;
                    case 4:
                        s = "Art";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT15:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Art";
                        break;
                    case 2:
                        s = "Study Hall";
                        break;
                    case 3:
                        s = "Writing";
                        break;
                    case 4:
                        s = "PE";
                        break;
                    default:
                        break;
                }
                break;
            case Period.ROT16:
                switch (sixthPrefs[3]) {
                    case 1:
                        s = "Study Hall";
                        break;
                    case 2:
                        s = "Art";
                        break;
                    case 3:
                        s = "PE";
                        break;
                    case 4:
                        s = "Latin";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return s;
    }

    private String periodNameNonSixth(int periodNumber, String[] periodNames, CurrentDate today) {
        String s = "";
        String advisoryType = advisoryType(today);
        switch (periodNumber) {
            case Period.BREAK:
                s = "Break";
                break;
            case Period.ASSEMBLY:
                s = "Assembly";
                break;
            case Period.CLASSMEETING:
                s = "Class Meeting";
                break;
            case Period.ADVISORY:
                s = "Advisory";
                break;
            case Period.CLUBS:
                s = "Clubs";
                break;
            case Period.LUNCH:
                s = "Lunch";
                if(advisoryType != null && advisoryType.equals("Advisory Lunch Meeting")) {
                    s = advisoryType;
                }
                break;
            case Period.SPORTS:
                s = "Sports";
                break;
            case Period.STUDYHALL:
                s = "Study Hall";
                break;
            case Period.ELECTIVES:
                s = "Electives";
                break;
            case Period.COMMUNITYTIME:
                s = communityTime(today);
                break;
            default:
                s = periodNames[periodNumber - 1];
                break;
        }
        return s;
    }


    private String communityTime(CurrentDate today) {
        switch (today.getDayOfTheWeek()) {
            case 2:
                return "Assembly";
            case 3:
                String advisoryType = advisoryType(today);
                if(advisoryType != null && advisoryType.equals("Advisory Lunch Meeting")) {
                    return "Break";
                }
                return advisoryType;
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
        System.out.println(c.advisoryType(new CurrentDate(9, 13, 2016)));
    }
}


