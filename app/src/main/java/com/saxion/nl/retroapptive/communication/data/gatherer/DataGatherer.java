package com.saxion.nl.retroapptive.communication.data.gatherer;



public interface DataGatherer {

	public abstract List<Project> getProjects();
	
	public abstract List<Sprint> getSprints(final Project sprint);

	public abstract List<Notitie> getNotities(final Sprint sprint);

	public abstract List<Notitie> getNotities(final Project sprint);

}
