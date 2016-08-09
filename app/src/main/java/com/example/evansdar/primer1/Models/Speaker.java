package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stinsocb on 7/6/2016.
 */
public class Speaker extends JSONObject {

    public String AtendeeId;
    public Attendee Attendee;
    public String City;
    public String Id;
    public String State;
    public List<String> Title;


    public Speaker(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getAtendeeId() {
        return AtendeeId;
    }

    public com.example.evansdar.primer1.Models.Attendee getAttendee() {
        return Attendee;
    }

    public String getCity() {
        return City;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return this.Attendee.FirstName + " " + this.Attendee.LastName;
    }

    public String getState() {
        return State;
    }

    public List<String> getTitle() {
        return Title;
    }

    public String getEmail() {
        return this.Attendee.getEmail();
    }

    public Speaker FromJson(JSONObject object) throws JSONException {

        this.AtendeeId = (String) object.get("AtendeeId");
        this.Attendee = new Attendee((JSONObject) object.get("Attendee"));
        this.City = (String) object.get("City");

        this.Id = (String) object.get("Id");
        this.State = (String) object.get("State");
        this.Title = Arrays.asList(((String) object.get("Title")).split("\\s*,\\s*"));

        return this;
    }
    @Override
    public String toString() {
        //TODO There is an error with institution object from api repsonse, so once that is fixed add institution to tostring method
        return this.Attendee.toString()
                + "\n" + this.Title.toString()
                + "\n" + "United Network for Organ Sharing"
                + "\n" + this.City + " " + this.State;
    }
}
