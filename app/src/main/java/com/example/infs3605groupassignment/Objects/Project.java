package com.example.infs3605groupassignment.Objects;

public class Project {
    private String name;
    private String description;
    private String category;
    private String funding;
    private String progress;
    private String country;
    private String company;

    public Project() {

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

    public Project(String name, String description, String category, String funding, String progress, String country, String company) {
        this.name = name;
        this.description = description;
        this.category = category;
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

    public void setOwner(String company) {
        this.company = company;
    }
}

