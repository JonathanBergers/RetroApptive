package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Sprint;

public class IsisSprint extends Sprint {
	
	private final String sprintURL;

	public IsisSprint(final IsisProject projectParent, final int sprintID, final String sprintURL) {
		super(projectParent, sprintID);
		this.sprintURL = sprintURL;
	}
	
	public String getSprintURL() {
		return sprintURL;
	}

}
