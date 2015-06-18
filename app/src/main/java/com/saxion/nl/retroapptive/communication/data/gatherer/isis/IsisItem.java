package com.saxion.nl.retroapptive.communication.data.gatherer.isis;

import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.Sprint;

public class IsisItem extends Item {

	private final String itemURL;

	public IsisItem(final Sprint sprint, final String description, final String summary, final Profiel profile, final String itemURL) {
		super(sprint, description, summary, profile);
		this.itemURL = itemURL;
	}

	public String getItemURL() {
		return itemURL;
	}
}
