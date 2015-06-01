package com.saxion.nl.retroapptive.communication.converter;

import java.util.Map;

import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Action;
import com.saxion.nl.retroapptive.model.UserStory;


public  class IsisConverter {

    private static IsisConverter isisConverter;

    public static IsisConverter getInstance(){
        if(isisConverter == null){

            return new IsisConverter();
        }
        return isisConverter;

    }

    public void convertObject(DomainObject domainObject){




        Map<String, ObjectMember> members = domainObject.getMembers();

        String type = members.get("category").getValue().getTextValue();
        Log.d("CONVERTER", members.get("category").getValue().getTextValue());

        if(type.equals("Note")){
            Log.d("CONVERTER", "NOTE CREATED");

            Model.getInstance().addNote(getNotitieFromDomainObject(domainObject));

        }

        if(type.equals("User Story")){

            Model.getInstance().addUserStory(getUserStoryFromDomainObject(domainObject));

        }

        if(type.equals("Action")){

            Model.getInstance().addAction(getReactionFromDomainObject(domainObject));

        }

        return;



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



        Log.d("CONVERTER", "NOTE CREATED");
        return notitie;


    }


    public UserStory getUserStoryFromDomainObject(DomainObject domainObject){


        Item item = getItemFromDomainObject(domainObject);
        Map<String, ObjectMember> members = domainObject.getMembers();

        UserStory userStory = new UserStory(item);

        boolean isBurned;

            isBurned = members.get("isPositive").getValue().getBooleanValue();
            userStory.setIsBurned(isBurned);


        double points = 0;
        if(members.get("points").getValue() !=null){

            String pointString = members.get("points").getValue().asText();
            points= Double.parseDouble(pointString.substring(0, pointString.length()));



        }
        int i = (int) points;
        userStory.setPoints(i);



        Log.d("CONVERTER", "USER STORY CREATED");
        return userStory;






    }

    public Action getReactionFromDomainObject(DomainObject domainObject){

        Item item = getItemFromDomainObject(domainObject);
        Map<String, ObjectMember> members = domainObject.getMembers();

        Action action = new Action(item);

        Integer priority = null;
        if(members.get("points").getValue() !=null){

            String points = members.get("points").getValue().asText();
            priority= Integer.parseInt(points.substring(0, points.length() -2));



        }
        action.setPriority(priority);



        Log.d("CONVERTER", "REACTION CREATED");
        return action;


    }













}
