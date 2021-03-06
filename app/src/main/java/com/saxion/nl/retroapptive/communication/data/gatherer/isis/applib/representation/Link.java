package com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation;

import org.codehaus.jackson.JsonNode;

import java.util.Map;

/* Author - Dimuthu Upeksha*/

public class Link extends JsonRepr {
    private String Id;
    private String title;
    private String rel;
    private String href;
    private String method;
    private String type;

    private Map<String, Map<String, JsonNode>> arguments;
    private Map<String, Map<String, JsonNode>> args;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Map<String, JsonNode>> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Map<String, JsonNode>> arguments) {
        this.arguments = arguments;
    }

    public Map<String, Map<String, JsonNode>> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Map<String, JsonNode>> args) {
        this.args = args;
    }


}
