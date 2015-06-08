package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Project;

public class IsisProject extends Project {
	
	private final String projectURL;

	public IsisProject(final String name, final String projectURL) {
		super(name);
		this.projectURL = projectURL;
	}
	
	public String getProjectURL() {
		return projectURL;
	}

}
