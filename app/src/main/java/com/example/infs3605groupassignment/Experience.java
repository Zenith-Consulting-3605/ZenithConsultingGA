package com.example.infs3605groupassignment;

public class Experience {
    private String title;
    private String employmentType;
    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private String description;

    public Experience() {

    }

    public Experience(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Experience(String title, String employmentType, String company, String location, String startDate, String endDate, String description) {
        this.title = title;
        this.employmentType = employmentType;
        this.company = company;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getEmploymentType() {return employmentType; }

    public void setEmploymentType(String employmentType) { this.employmentType = employmentType;}

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getStartDate() { return startDate; }

    public void setStartDate() { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
