package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Profiel;

public class IsisProfiel extends Profiel {
	
	private final String profielURL;

	public IsisProfiel(final String name, final String profielURL) {
		super(name);
		this.profielURL = profielURL;
	}
	
	public String getProfielURL() {
		return profielURL;
	}

}
