package com.example.shayng.capstone;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Event {
    private String startTime;
    private String endTime;
    private String title;
    private String location;
    private String description;
    private String urlLink;

    private Calendar now;
    private Boolean isHappening;
    private Boolean isOver;

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }


    public Event(String title, String start, String end, String location, String description) {
        this.startTime = start;//"the";//startTime;//.equals("0")? "00":startTime;
        this.title = title;
        this.location = location;
        this.description = description;
        this.endTime = end;//"then";//endTime;//.equals("0")? "00":endTime;
        //date = new Date();
        //date.setMonth();
        //now = Calendar.getInstance();

        //if(now.after(start) && now.before(end)) isHappening = true; else isHappening = false;

        //setUrlLink("");
    }

    public Event(Calendar startTime, Calendar endTime, String title, String location, String description, String url) {
        //this.startTime = startTime.to;//.equals("0")? "00":startTime;
        this.title = title;
        this.location = location;
        this.description = description;
       // this.endTime = endTime;//.equals("0")? "00":endTime;
        //date = new Date();
        //date.setMonth();

        //setUrlLink("");
    }

    public Event(String startTime, String endTime, String title, String location, String description, String urLink) {
        //this.startTime = startTime;//.equals("0")? "00":startTime;
        this.title = title;
        this.location = location;
        this.urlLink = urLink;
        this.description = description;
        //this.endTime = endTime;//.equals("0")? "00":endTime;
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
