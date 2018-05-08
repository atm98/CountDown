package com.agnt45.countdown.Classes;

/**
 * Created by an160 on 07-05-2018.
 */

public class Events {
    public Events() {

    }

    private String EventName;
    private String EventDate;
    private String EventTime;
    private String EventLocatino;
    private String[] EventLinks;

    public String getEventPicUril() {
        return EventPicUril;
    }

    public void setEventPicUril(String eventPicUril) {
        EventPicUril = eventPicUril;
    }

    private String EventPicUril;
    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

    public String getEventLocatino() {
        return EventLocatino;
    }

    public void setEventLocatino(String eventLocatino) {
        EventLocatino = eventLocatino;
    }

    public String[] getEventLinks() {
        return EventLinks;
    }

    public void setEventLinks(String[] eventLinks) {
        EventLinks = eventLinks;
    }


    public Events(String eventName, String eventDate, String eventTime, String eventLocatino, String[] eventLinks,String eventPicUril) {
        EventName = eventName;
        EventDate = eventDate;
        EventTime = eventTime;
        EventLocatino = eventLocatino;
        EventLinks = eventLinks;
        EventPicUril = eventPicUril;
    }



}
