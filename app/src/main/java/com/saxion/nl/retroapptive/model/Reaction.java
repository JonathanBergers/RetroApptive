package com.saxion.nl.retroapptive.model;

public class Reaction extends Item{
	
	private Integer priority;



	public Reaction(Item item) {
		super(item);

	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
