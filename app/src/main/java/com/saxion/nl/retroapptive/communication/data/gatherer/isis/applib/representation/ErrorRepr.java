package com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation;

import org.codehaus.jackson.JsonNode;

import java.util.List;
import java.util.Map;

/* Author - Dimuthu Upeksha*/

public class ErrorRepr extends JsonRepr {
    private String message;
    private List<String> stackTrace;
    private Map<String, JsonNode> causedBy;
    private JsonNode extensions;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Map<String, JsonNode> getCausedBy() {
        return causedBy;
    }

    public void setCausedBy(Map<String, JsonNode> causedBy) {
        this.causedBy = causedBy;
    }

    public JsonNode getExtensions() {
        return extensions;
    }

    public void setExtensions(JsonNode extensions) {
        this.extensions = extensions;
    }

}
