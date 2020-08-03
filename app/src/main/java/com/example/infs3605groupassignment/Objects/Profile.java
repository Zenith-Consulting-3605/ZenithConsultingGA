package com.example.infs3605groupassignment.Objects;

public class Profile {

    private String first_name;
    private String last_name;
    private String occupation;
    private String location;

    public Profile(String first_name, String last_name, String location, String occupation) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.occupation = occupation;
        this.location = location;
    }

    public Profile(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
