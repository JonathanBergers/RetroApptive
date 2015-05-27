package com.saxion.nl.retroapptive.model;

public class Notitie extends Item {
	
	private String category; // of Category category
	private boolean isPositive;
	private Reaction reaction; //standaard null
	
	//TODO attachment, blob ofzo
	
	public Notitie(String description, String summary, Sprint sprint, String category, boolean isPositive) {
		super(description, summary, sprint);
		this.category = category;
		this.isPositive = isPositive;
	}

	public String getCategory() {
		return category;
	}

	public boolean isPositive() {
		return isPositive;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}
	

}
