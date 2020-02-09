package com.imapro.firecloud;

import com.google.firebase.firestore.Exclude;

import java.util.List;

public class Note {
    private String documentId;
    private String title;
    private String description;
    private int priority;
    List<String> tags;

    public Note(String title, String description, int priority){
        //public no-arg constructor needed
    }

    public Note(String documentId, String title, String description, int priority, List<String> tags) {
        this.documentId = documentId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.tags = tags;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return tags;
    }
}