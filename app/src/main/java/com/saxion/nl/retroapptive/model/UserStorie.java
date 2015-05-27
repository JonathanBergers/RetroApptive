package com.saxion.nl.retroapptive.model;

public class UserStorie extends Item {
	
	private int points;
	private boolean isBurned = false; //standaard false

	public UserStorie(String description, String summary, Sprint sprint, int points) {
		super(description, summary, sprint);
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public boolean isBurned() {
		return isBurned;
	}
	
	/**
	 * zet de waarde van isBurned naar niet isBurned
	 * @return de nieuwe waarde van isBurned
	 */
	public boolean setBurned() {
		this.isBurned = !isBurned;
		return isBurned;
	}

}
