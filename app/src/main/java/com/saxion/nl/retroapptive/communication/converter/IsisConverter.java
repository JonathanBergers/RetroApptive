package com.saxion.nl.retroapptive.communication.converter;

import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Project;
import com.saxion.nl.retroapptive.model.Sprint;


public class IsisConverter {




    public Notitie getNotitieFromDomainObject(DomainObject input){

        //alle atributen van input
        Map<String, ObjectMember> members = input.getMembers();
        Set<String> keys = members.keySet();

        /*
        for(String key : keys){
            Log.d("Notite", "NOTITIE key: "+key);
        }
        */

        //de valiues waar je iets aan hebt
        String descreption = members.get("description").getValue().asText();
        String summary = members.get("summary").getValue().asText();
        String category = members.get("category").getValue().asText();
        String sprintNumber = members.get("sprintNumber").getValue().asText();
        String ispositive = members.get("isPositive").getValue().asText();




        Log.d("Notite", "NOTITIE descreption: "+descreption);
        Log.d("Notite", "NOTITIE summary: "+summary);
        Log.d("Notite", "NOTITIE category: "+category);
        Log.d("Notite", "NOTITIE sprintNumber: "+sprintNumber);
        Log.d("Notite", "NOTITIE isPositive: "+ispositive);


        //TODO hier moet iets komen om de goeie sprint te koppelen
        Project project = new Project("OK");
        Sprint sprint = new Sprint(project);

        Notitie notitie = new Notitie(descreption, summary, sprint, category, ispositive.equals("true"));
        //Notitie notitie = new Notitie(description, summary, sprint, category, isPositive);

        return notitie;


    }



}
