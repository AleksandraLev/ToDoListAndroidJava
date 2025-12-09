package com.example.finalproject.model;

public class Item {
    private long id;
    private String title;
    private String description;
    private boolean done;

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
        this.done = false;
    }

    public Item(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = false;
    }

    public Item(long id, String title, String description, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
}
