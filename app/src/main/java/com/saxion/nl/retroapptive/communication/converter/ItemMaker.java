package com.saxion.nl.retroapptive.communication.converter;

import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Notitie;

/**
 * Created by jonathan on 28-5-15.
 */
public class ItemMaker {




    public Item makeItem(String description, String summary, int sprintNumber){

            Item item = new Item(description, summary,sprintNumber);

        return item;


    }





}
