package com.saxion.nl.retroapptive.model;

public class UserStory extends Item {
	
	private Integer points;
	private boolean isBurned = false; //standaard false

	public UserStory(Item item) {
		super(item);

	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public void setIsBurned(boolean isBurned) {
		this.isBurned = isBurned;
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
