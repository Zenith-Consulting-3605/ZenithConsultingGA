package com.example.infs3605groupassignment.Objects;

public class Project {
    private String name;
    private String description;
    private String category;
    private String funding;
    private String progress;
    private String country;
    private String company;
    private int ID;
    private int owner;
    private String imageURL;

    public Project() {

    }

    public Project(int ID) {
        this.ID = ID;
    }

    public Project(int ID, int owner, String name) {
        this.ID = ID;
        this.owner = owner;
        this.name = name;
    }

    public Project(int ID, String name, String company, String category) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.category = category;
    }

    public Project(int ID, String name, String company, String category, String imageURL) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.category = category;
        this.imageURL = imageURL;
    }

    public Project(String name, String company, String category) {
        this.name = name;
        this.company = company;
        this.category = category;
    }

    public Project(String name, String company, String category, String progress) {
        this.name = name;
        this.company = company;
        this.category = category;
        this.progress = progress;
    }

    public Project(String name, String funding, String company, String country, String description, String progress) {
        this.name = name;
        this.funding = funding;
        this.company = company;
        this.country = country;
        this.description = description;
        this.progress = progress;
    }

    public Project(int ID, String name, String funding, String company, String country, String description, String progress, String category) {
        this.ID = ID;
        this.name = name;
        this.funding = funding;
        this.company = company;
        this.country = country;
        this.description = description;
        this.progress = progress;
        this.category = category;
    }

    public Project(int ID, String name, String funding, String company, String country, String description, String progress, String category, String imageURL) {
        this.ID = ID;
        this.name = name;
        this.funding = funding;
        this.company = company;
        this.country = country;
        this.description = description;
        this.progress = progress;
        this.category = category;
        this.imageURL = imageURL;
    }

    public Project(String name, String description, String category, String funding, String progress, String country, String company) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.funding = funding;
        this.progress = progress;
        this.country = country;
        this.company = company;
    }

    public Project(int ID, String name, String funding, String company, String country, String description, String progress) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.funding = funding;
        this.progress = progress;
        this.country = country;
        this.company = company;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getFunding() {
        return funding;
    }

    public String getProgress() {
        return progress;
    }

    public String getCountry() {
        return country;
    }

    public String getCompany() {
        return company;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getID() { return ID; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setID(int ID) { this.ID = ID; }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

