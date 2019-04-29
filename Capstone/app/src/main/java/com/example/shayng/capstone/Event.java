package com.example.shayng.capstone;
import java.util.ArrayList;
import java.util.Date;


public class Event {
    private String startTime;
    private String endTime;
    private Date date;
    private String title;
    private String location;
    private String description;
    private String urlLink;

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }


    public Event(String startTime, String title, String location, String description) {
        this.startTime = startTime;
        this.title = title;
        this.location = location;
        this.description = description;
    }

    public Event(String startTime, String title, String location, String description, String urLink) {
        this.startTime = startTime;
        this.title = title;
        this.location = location;
        this.urlLink = urLink;
        this.description = description;
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }

    public void setEndTime(String time){
        this.endTime = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
