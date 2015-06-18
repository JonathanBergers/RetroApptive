package com.saxion.nl.retroapptive.model;

public class Item {


	private final Profiel profile;
	private final Sprint sprint;
	private final String description;
	private final String summary;
	private  Profiel profiel;

	public Item(final Sprint sprint, final String description, final String summary, Profiel profile) {
		this.description = description;
		this.summary = summary;
		this.sprint = sprint;
		this.profile = profile;
	}

	public Project getProject() {
		return getSprint().getProject();
	}

	public Sprint getSprint() {
		return sprint;
	}

	public String getDescription() {
		return description;
	}

	public String getSummary() {
		return summary;
	}

	public Profiel getProfile() {
		return profile;
	}
}
