package com.saxion.nl.retroapptive.model;

public class Action extends Item{
	
	private Integer priority;

	public Action(Item item) {
		super(item);

	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
