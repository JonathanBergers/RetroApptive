package com.saxion.nl.retroapptive.model;

public class Notitie extends Item {

    private final Profiel profile;
    private final boolean isPositive;
    private final String subcategory;

    public Notitie(final Sprint sprint, final String description, final String summary, final Profiel profile, final boolean isPositive, final String subcategory) {
        super(sprint, description, summary);
        this.profile = profile;
        this.isPositive = isPositive;
        this.subcategory = subcategory;
    }

    public Profiel getProfile() {
        return profile;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public String getSubcategory() {
        return subcategory;
    }

}
