package com.example.infs3605groupassignment.Profile;

public class Skill {
    private String name;
    private String description;
    private int dummy;

    public Skill() {

    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Skill(String name, String description, int dummy) {
        this.name = name;
        this.description = description;
        this.dummy = dummy;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getDummy() { return dummy; }

    public void setDummy(int dummy) { this.dummy = dummy; };
}
