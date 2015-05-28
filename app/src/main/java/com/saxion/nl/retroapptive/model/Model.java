package com.saxion.nl.retroapptive.model;


import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Homepage;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;

import java.util.ArrayList;
import java.util.Map;

public class Model {




    static Model instance;
    static final Object lock = new Object();
    /* The Model */
    private Homepage homePage;

    private Link currentLink;

    public ArrayList<String> notesTestStrings = new ArrayList<>();
    public ArrayList<Notitie> notes = new ArrayList<>();


    private Map<String , ObjectMember> todoItemMembers;

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


    
    
}
