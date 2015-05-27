package com.saxion.nl.retroapptive.communication.converter;

import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;



public class IsisConverter {




    public Notitie getNotitieFromDomainObject(DomainObject input){

        //alle atributen van input
        Map<String, ObjectMember> members = input.getMembers();
        Set<String> keys = members.keySet();

        for(String key : keys){
            Log.d("Notite", "NOTITIE key: "+key);
        }

        //de valiues waar je iets aan hebt
        String descreption = members.get("description").getValue().asText();
        String category = members.get("category").getValue().asText();




        Log.d("Notite", "NOTITIE descreption: "+descreption);
        Log.d("Notite", "NOTITIE category: "+category);



        Notitie notitie = new Notitie(null, null, null, null, false);
        //Notitie notitie = new Notitie(description, summary, sprint, category, isPositive);

        return notitie;


    }



}
