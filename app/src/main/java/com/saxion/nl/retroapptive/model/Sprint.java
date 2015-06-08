package com.saxion.nl.retroapptive.model;

public class Sprint {

    private final Project project;
    private final int sprintID;

    public Sprint(final Project project, final int id) {
        this.project = project;
        this.sprintID = id;
    }

    public Project getProject() {
        return project;
    }

    public int getSprintID() {
        return sprintID;
    }

}
