package com.saxion.nl.retroapptive.communication.data.gatherer;

import com.saxion.nl.retroapptive.communication.login.LoginCredentials;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

public interface DataGatherer {
	
	public abstract int login(final LoginCredentials loginCredentials);

	/**
	 * Blocking method, gets all projects
	 * 
	 * @return
	 */
	public abstract List<Project> getProjects() throws Exception;

	/**
	 * Blocking method
	 * 
	 * @param project
	 * @return
	 */
	public abstract List<Sprint> getSprints(final Project project) throws Exception;

	/**
	 * Blocking method
	 * 
	 * @param sprint
	 * @return
	 */
	public abstract List<Item> getItems(final Sprint sprint) throws Exception;

	/**
	 * Blocking method
	 * 
	 * @param project
	 * @return
	 */
	public abstract Map<Sprint, List<Item>> getItems(final Project project) throws Exception;
	
	public abstract List<String> getPossibleNoteSubcategories(final Sprint sprint) throws Exception;

	public abstract Notitie createNote(final Sprint sprint, final String description, final String summary, final boolean positive, final String subcategory) throws Exception;
	
	public abstract void deleteNote(final Notitie note) throws Exception;

	public abstract void addAttachment(Notitie note, byte[] attachment) throws IOException;
}
