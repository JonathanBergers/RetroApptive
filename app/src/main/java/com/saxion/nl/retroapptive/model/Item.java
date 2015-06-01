package com.saxion.nl.retroapptive.model;

public class Item {

	private String description, summary;
	private int sprintNumber;
	
	public Item(String description, String summary, int sprintNumber) {
		this.description = description;
		this.summary = summary;
		this.sprintNumber = sprintNumber;
	}

	public Item(Item item){
		this.description = item.getDescription();
		this.summary = item.getSummary();
		this.sprintNumber = item.getSprintNumber();
	}

	public String getDescription() {
		return description;
	}

	public String getSummary() {
		return summary;
	}

	public int getSprintNumber() {
		return sprintNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	

	
	
	
}
