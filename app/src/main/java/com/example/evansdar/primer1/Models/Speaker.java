package com.example.evansdar.primer1.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
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

    public Speaker FromJson(JSONObject object) throws JSONException {

        this.AtendeeId = (String) object.get("AtendeeId");
        this.Attendee = new Attendee().FromJson((JSONObject) object.get("Attendee"));
        this.City = (String) object.get("City");
        this.Id = (String) object.get("Id");
        this.State = (String) object.get("State");
        this.Title = (List<String>) object.get("Title");

        return this;

    }
}