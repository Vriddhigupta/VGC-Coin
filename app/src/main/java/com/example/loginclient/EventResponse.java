package com.example.loginclient;

public class EventResponse {
    private String _id;
    private String eventName;
    private String eventDescription;
    private String eventVenue;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventCommittee;
    private String eventContact;
    private String eventFile;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventCommittee() {
        return eventCommittee;
    }

    public void setEventCommittee(String eventCommittee) {
        this.eventCommittee = eventCommittee;
    }

    public String getEventContact() {
        return eventContact;
    }

    public void setEventContact(String eventContact) {
        this.eventContact = eventContact;
    }

    public String getEventFile() {
        return eventFile;
    }

    public void setEventFile(String eventFile) {
        this.eventFile = eventFile;
    }
}
