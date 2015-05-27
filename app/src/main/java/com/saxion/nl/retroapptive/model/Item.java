package com.saxion.nl.retroapptive.model;

public abstract class Item {

	private String description, summary;
	private Sprint sprint;
	
	public Item(String description, String summary, Sprint sprint) {
		this.description = description;
		this.summary = summary;
		this.sprint = sprint;
	}

	public String getDescription() {
		return description;
	}

	public String getSummary() {
		return summary;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	
	
}
