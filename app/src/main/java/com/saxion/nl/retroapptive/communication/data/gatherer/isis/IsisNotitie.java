package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Sprint;

public class IsisNotitie extends Notitie {

	private final String notitieURL;

	public IsisNotitie(final Sprint sprint, final String description, final String summary, final Profiel profile, final boolean isPositive, final String subcategory, final String notitieURL) {
		super(sprint, description, summary, profile, isPositive, subcategory);
		this.notitieURL = notitieURL;
	}

	public String getNotitieURL() {
		return notitieURL;
	}

}
