package com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation;

import java.util.Map;

/* Author - Dimuthu Upeksha*/

public class DomainTypeProperty extends JsonRepr {
    private String id;
    private String memberType;
    private boolean optional;
    private Map<String, String> extensions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Map<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

}
