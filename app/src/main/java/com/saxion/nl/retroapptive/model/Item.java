package com.saxion.nl.retroapptive.model;

public class Item {

	private final Sprint sprint;
	private final String description;
	private final String summary;
	private  Profiel profiel;

	public Item(final Sprint sprint, final String description, final String summary) {
		this.description = description;
		this.summary = summary;
		this.sprint = sprint;
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

	public Profiel getProfiel() {
		//TODO
		return new Profiel(Profiel.ProfielType.USER, "voornaam");
		//return profiel;
	}
}
