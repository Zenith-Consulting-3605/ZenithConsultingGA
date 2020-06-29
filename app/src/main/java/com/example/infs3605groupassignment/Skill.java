package com.example.infs3605groupassignment;

public class Skill {
    private String name;
    private String description;

    public Skill() {

    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
