package com.saxion.nl.retroapptive.model;

public class Notitie extends Item {

    private String category; // of Category category
    private Boolean isPositive;

    //TODO attachment, blob ofzo

    public Notitie(Item item) {
        super(item);

    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public Boolean isPositive() {
        return isPositive;
    }
}
