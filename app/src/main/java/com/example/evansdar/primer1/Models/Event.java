package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wattsbrc on 7/14/2016.
 */
public class Event extends JSONObject {

    public String StartTimeUtc;
    public String EndTimeUtc;
    public String Title;
    public String Locationid;
    public String EventLocation;
    public List<String> Presenters;

    public Event() {
    }

    public Event(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Event FromJson(JSONObject object) throws JSONException {

        this.StartTimeUtc = (String) object.get("StartTimeUtc");
        this.EndTimeUtc = object.getString("EndTimeUtc");
        this.Title = object.getString("Title");
        this.Locationid = (String) object.get("Locationid");
        this.EventLocation = (String) object.get("EventLocation");
        this.Presenters = Arrays.asList(((String) object.get("Presenters")).split("\\s*,\\s*"));

        return this;

    }
}
