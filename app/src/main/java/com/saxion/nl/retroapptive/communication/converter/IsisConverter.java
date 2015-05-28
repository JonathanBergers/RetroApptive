package com.saxion.nl.retroapptive.communication.converter;

import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Reaction;
import com.saxion.nl.retroapptive.model.Sprint;
import com.saxion.nl.retroapptive.model.UserStory;


public  class IsisConverter {

    private static IsisConverter isisConverter;

    public static IsisConverter getInstance(){
        if(isisConverter == null){

            return new IsisConverter();
        }
        return isisConverter;

    }

    public Item getItemFromDomainObject(DomainObject domainObject){


        Map<String, ObjectMember> members = domainObject.getMembers();
        String description = members.get("description").getValue().getTextValue();
        String summary = members.get("summary").getValue().getTextValue();
        int sprintNumber = members.get("sprintNumber").getValue().getIntValue();

        Item item = new Item(description, summary, sprintNumber);
        return item;


    }



    public Notitie getNotitieFromDomainObject(DomainObject domainObject){


        Item item = getItemFromDomainObject(domainObject);


        Notitie notitie = new Notitie(item);



        Map<String, ObjectMember> members = domainObject.getMembers();

        String category;
        Boolean isPositive;

        if(members.get("subcategory").getValue() !=null){

            category = members.get("subcategory").getValue().getTextValue();
            notitie.setCategory(category);
        }




            isPositive = members.get("isPositive").getValue().getBooleanValue();
            notitie.setIsPositive(isPositive);



        return notitie;


    }


    public UserStory getUserStoryFromDomainObject(DomainObject domainObject){


        Item item = getItemFromDomainObject(domainObject);
        Map<String, ObjectMember> members = domainObject.getMembers();

        UserStory userStory = new UserStory(item);

        Boolean isBurned;

            isBurned = members.get("isPositive").getValue().getBooleanValue();
            userStory.setIsBurned(isBurned);


        Integer points = null;
        if(members.get("points").getValue() !=null){

            points = Integer.parseInt(members.get("points").getValue().asText());

        }
        userStory.setPoints(points);



        return userStory;






    }

    public Reaction getReactionFromDomainObject(DomainObject domainObject){

        Item item = getItemFromDomainObject(domainObject);
        Map<String, ObjectMember> members = domainObject.getMembers();

        Reaction reaction = new Reaction(item);

        Integer priority = null;
        if(members.get("points").getValue() !=null){

            priority = Integer.parseInt(members.get("points").getValue().asText());

        }
        reaction.setPriority(priority);

        return reaction;


    }













}
