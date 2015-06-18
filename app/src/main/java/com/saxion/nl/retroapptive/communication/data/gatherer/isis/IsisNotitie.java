package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Sprint;

public class IsisNotitie extends Notitie {

	private final String notitieURL;

	public IsisNotitie(final Sprint sprint, final IsisItem isisItem, final boolean isPositive, final String subcategory) {
		super(sprint, isisItem.getDescription(), isisItem.getSummary(), isisItem.getProfile(),isPositive, subcategory);
		this.notitieURL = isisItem.getItemURL();
	}

	public String getNotitieURL() {
		return notitieURL;
	}

}
