package com.saxion.nl.retroapptive.model;


import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Homepage;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {

    private static Model instance;
    static final Object lock = new Object();
    /* The Model */
    private Homepage homePage;

    private Link currentLink;

    private List<String> notesTestStrings = new ArrayList<>();
    private List<Notitie> notes = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();
    private List<UserStory> userStories = new ArrayList<>();

    private Map<String, ObjectMember> todoItemMembers;

    public Map<String, ObjectMember> getTodoItemMembers() {
        return todoItemMembers;
    }

    public void setTodoItemMembers(Map<String, ObjectMember> todoItemMembers) {
        this.todoItemMembers = todoItemMembers;
    }

    public Link getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(Link currentLink) {
        this.currentLink = currentLink;
    }

    public static Model getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new Model();
            }
            return instance;
        }
    }

    public Homepage getHomePage() {
        return homePage;
    }

    public void setHomePage(Homepage homePage) {
        this.homePage = homePage;
    }

    public void addNote(Notitie note) {
        notes.add(note);
    }

    public List<Notitie> getNotes() {
        return notes;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addUserStory(UserStory userStory) {
        userStories.add(userStory);
    }

    public Action getAction(int position) {
        return actions.get(position);
    }

    public Notitie getNote(int position) {
        return notes.get(position);
    }

    public UserStory getUserStory(int position) {
        return userStories.get(position);
    }
}
