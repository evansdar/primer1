package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stinsocb on 7/14/2016.
 */
public class EventPresenter extends JSONObject{

    public String AttendeeId;
    public Attendee Attendee;
    public String EventId;

    public EventPresenter() {
    }

    public EventPresenter(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public EventPresenter FromJson(JSONObject object) throws JSONException {

        this.AttendeeId = (String) object.get("AttendeeId");
        this.Attendee = new Attendee((JSONObject) object.get("Attendee"));
        this.EventId = (String) object.get("EventId");

        return this;
    }
    @Override
    public String toString() {
        //TODO There is an error with institution object from api repsonse, so once that is fixed add institution to tostring method
        return this.Attendee.toString();
    }
}
