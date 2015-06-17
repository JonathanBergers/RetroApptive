package com.saxion.nl.retroapptive.model;

public class Profiel {

	public enum ProfielType {
		USER,
		SCRUM_MASTER
	}

	private final ProfielType profileType;
	
	private final String name;
	private final String surname;

	public Profiel(final ProfielType profileType, final String name) {
		this(profileType, name, "");
	}
	
	public Profiel(final ProfielType profileType, final String name, final String surname) {
		this.profileType = profileType;
		this.name = name;
		this.surname = surname;
	}

	public ProfielType getProfileType() {
		return profileType;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
}
