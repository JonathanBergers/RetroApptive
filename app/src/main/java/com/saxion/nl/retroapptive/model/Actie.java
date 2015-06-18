package com.saxion.nl.retroapptive.model;

public class Actie extends Item {

	private final Profiel profile;
	private final int priority;

	public Actie(final Sprint sprint, final String description, final String summary, final Profiel profile, final int priority) {
		super(sprint, description, summary, profile);
		this.profile = profile;
		this.priority = priority;
	}

	public Profiel getProfile() {
		return profile;
	}

	public int getPriority() {
		return priority;
	}

}
