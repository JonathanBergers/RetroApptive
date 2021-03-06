package com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation;

import org.codehaus.jackson.JsonNode;

import java.util.Map;

/* Author - Dimuthu Upeksha*/

public class ObjectMember extends JsonRepr {
    private String id;
    private String memberType;
    private String disabledReason;
    private JsonNode value; // for collections
    private String format;
    // @JsonProperty("x-isis-format")
    // public String x_isis_format;

    private Map<String, String> extensions;

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getDisabledReason() {
        return disabledReason;
    }

    public void setDisabledReason(String disabledReason) {
        this.disabledReason = disabledReason;
    }

    public String getFormat() {
        return format;
    }

    public JsonNode getValue() {
        return value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Map<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
