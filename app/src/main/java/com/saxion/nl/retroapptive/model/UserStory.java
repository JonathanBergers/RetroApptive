package com.saxion.nl.retroapptive.model;

public class UserStory extends Item {

    private Integer points;
    private Boolean isBurned = false; //standaard false

    public UserStory(Item item) {
        super(item);
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setIsBurned(Boolean isBurned) {
        this.isBurned = isBurned;
    }

    public int getPoints() {
        return points;
    }

    public Boolean isBurned() {
        return isBurned;
    }

    /**
     * zet de waarde van isBurned naar niet isBurned
     *
     * @return de nieuwe waarde van isBurned
     */
    public Boolean changeBurned() {

        if (isBurned == null) {
            isBurned = true;
            return isBurned;
        }
        this.isBurned = !isBurned;
        return isBurned;
    }

}
