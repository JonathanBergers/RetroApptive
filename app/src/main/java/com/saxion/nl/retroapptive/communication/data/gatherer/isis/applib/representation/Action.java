package com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation;

import org.codehaus.jackson.JsonNode;

import java.util.List;
import java.util.Map;

/* Author - Dimuthu Upeksha*/

public class Action extends JsonRepr {
    private String id;
    private transient Map<String, Map<String, JsonNode>> parameters;
    private Map<String, Object> args = null;

    private String memberType;
    private String disabledReason;

    public String getDisabledReason() {
        return disabledReason;
    }

    public void setDisabledReason(String disabledReason) {
        this.disabledReason = disabledReason;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    private Map<String, String> extensions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Map<String, JsonNode>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Map<String, JsonNode>> parameters) {
        this.parameters = parameters;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Map<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

}
