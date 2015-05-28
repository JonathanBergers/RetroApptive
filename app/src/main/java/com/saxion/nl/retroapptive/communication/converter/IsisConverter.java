package com.saxion.nl.retroapptive.communication.converter;

import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;


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

        if(members.containsKey("category")){

            category = members.get("category").getValue().getTextValue();
            notitie.setCategory(category);
        }

        if(members.containsKey("isPositive")){

            isPositive = members.get("isPositive").getValue().getBooleanValue();
            notitie.setIsPositive(isPositive);
        }


        return notitie;


    }











}
