package com.saxion.nl.retroapptive.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.saxion.nl.retroapptive.communication.data.gatherer.DataGatherer;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.ApacheIsisDataGatherer;
import com.saxion.nl.retroapptive.communication.login.LoginCredentials;

public class Model implements DataGatherer {

    private static Model instance;

    private DataGatherer dataGathererImpl;


    private Item currentItem;
    private Item createdItem;

    public Item getCreatedItem() {
        return createdItem;
    }

    public void setCreatedItem(Item createdItem) {
        this.createdItem = createdItem;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public Model(final DataGatherer dataGatherer) {
        this.dataGathererImpl = dataGatherer;
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model(new ApacheIsisDataGatherer(ApacheIsisDataGatherer.HOST));
        }
        return instance;
    }

    public Profiel getLocalProfile() {
        return new Profiel(Profiel.ProfielType.USER, "LocalProfileTemp");
    }

    @Override
    public int login(final LoginCredentials loginCredentials) {
        return dataGathererImpl.login(loginCredentials);
    }

    @Override
    public void joinProject(final String projectIdentifier) throws Exception {
        dataGathererImpl.joinProject(projectIdentifier);
    }

    @Override
    public List<Project> getProjects() throws Exception {
        return dataGathererImpl.getProjects();
    }

    @Override
    public List<Profiel> getMembers(Project project) throws Exception {
        return dataGathererImpl.getMembers(project);
    }

    @Override
    public List<Sprint> getSprints(Project project) throws Exception {
        return dataGathererImpl.getSprints(project);
    }

    @Override
    public List<Item> getItems(Sprint sprint) throws Exception {
        return dataGathererImpl.getItems(sprint);
    }

    @Override
    public Map<Sprint, List<Item>> getItems(Project project) throws Exception {
        return dataGathererImpl.getItems(project);
    }

    @Override
    public List<String> getPossibleNoteSubcategories(Sprint sprint) throws Exception {
        return dataGathererImpl.getPossibleNoteSubcategories(sprint);
    }

    @Override
    public Notitie createNote(Sprint sprint, String description, String summary, boolean positive, String subcategory) throws Exception {
        return dataGathererImpl.createNote(sprint, description, summary, positive, subcategory);
    }

    @Override
    public void deleteNote(Notitie note) throws Exception {
        dataGathererImpl.deleteNote(note);
    }

    @Override
    public void addAttachment(Notitie note, byte[] attachment) throws IOException {
        dataGathererImpl.addAttachment(note, attachment);
    }


}
