package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Sprint;

public class IsisActie extends Actie {

	private final String actieURL;

	public IsisActie(final Sprint sprint, final IsisItem isisItem,final int priority) {
		super(sprint, isisItem.getDescription(), isisItem.getSummary(), isisItem.getProfile(), priority);
		this.actieURL = isisItem.getItemURL();

	}

	public String getActieURL() {
		return actieURL;
	}

}
