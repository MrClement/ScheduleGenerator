package canvasoutput;

import generator.CurrentDate;

public class Event {

	private CurrentDate today;
	private String startTime;
	private String description;
	private String endTime;
	private String title;

	public CurrentDate getToday() {
		return today;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getDescription() {
		return description;
	}

	public String getEndTime() {
		return endTime;
	}

	public Event(CurrentDate today) {
		this.today = today;
	}

	public void setStartTime(String string) {
		this.startTime = string;

	}

	public void setDescription(String periodName) {
		this.description = periodName;

	}

	public void setEndTime(String string) {
		this.endTime = string;

	}

	@Override
	public String toString() {
		return "Event [today=" + today + ", startTime=" + startTime + ", description=" + description + ", endTime="
				+ endTime + "]";
	}

	public String getTitle() {
		return title;
	}

}
