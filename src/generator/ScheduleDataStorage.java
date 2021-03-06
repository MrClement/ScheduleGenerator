package generator;

import java.util.HashMap;

public class ScheduleDataStorage {

	private CurrentDate startDate;
	private CurrentDate endDate;
	private SchoolType school;
	private boolean includeBreaksAndLunch;
	private HashMap<Character, Integer> singleBox;
	private int[] periodsToInclude;
	private String[] periodNames;
	private String filename;
	private boolean midSchoolElective;
	private boolean sixth;
	private int[] sixthPrefs;

	public ScheduleDataStorage() {
		startDate = new CurrentDate();
		endDate = new CurrentDate();
		school = SchoolType.HIGH;
		midSchoolElective = false;
		includeBreaksAndLunch = false;
		singleBox = new HashMap<Character, Integer>();
		periodsToInclude = new int[8];
		periodNames = new String[8];
		filename = "default.txt";
		sixth = false;
		sixthPrefs = new int[0];

	}

	public CurrentDate getStartDate() {
		return startDate;
	}

	public CurrentDate getEndDate() {
		return endDate;
	}

	public SchoolType getSchool() {
		return school;
	}

	public boolean isIncludeBreaksAndLunch() {
		return includeBreaksAndLunch;
	}

	public HashMap<Character, Integer> getSingleBox() {
		return singleBox;
	}

	public int[] getPeriodsToInclude() {
		return periodsToInclude;
	}

	public String[] getPeriodNames() {
		return periodNames;
	}

	public void setStartDate(CurrentDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(CurrentDate endDate) {
		this.endDate = endDate;
	}

	public void setSchool(SchoolType school) {
		this.school = school;
	}

	public void setIncludeBreaksAndLunch(boolean includeBreaksAndLunch) {
		this.includeBreaksAndLunch = includeBreaksAndLunch;
	}

	public void setSingleBox(HashMap<Character, Integer> singleBox) {
		this.singleBox = singleBox;
	}

	public void setPeriodsToInclude(int[] periodsToInclude) {
		this.periodsToInclude = periodsToInclude;
	}

	public void setPeriodNames(String[] periodNames) {
		this.periodNames = periodNames;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isMidSchoolElective() {
		return midSchoolElective;
	}

	public void setMidSchoolElective(boolean midSchoolElective) {
		this.midSchoolElective = midSchoolElective;
	}

	public boolean isSixth() {
		return sixth;
	}

	public void setSixth(boolean sixth) {
		this.sixth = sixth;
	}

	public int[] getSixthPrefs() {
		return sixthPrefs;
	}

	public void setSixthPrefs(int[] sixthPrefs) {
		this.sixthPrefs = sixthPrefs;
	}


}
