package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Sprint;

public class IsisActie extends Actie {

	private final String actieURL;

	public IsisActie(final Sprint sprint, final String description, final String summary, final Profiel profile, final int priority, final String actieURL) {
		super(sprint, description, summary, profile, priority);
		this.actieURL = actieURL;
	}

	public String getActieURL() {
		return actieURL;
	}

}
