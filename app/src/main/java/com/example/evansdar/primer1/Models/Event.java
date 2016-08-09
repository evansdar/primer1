package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wattsbrc on 7/14/2016.
 */
public class Event extends JSONObject {

    public String StartTimeUtc;
    public String EndTimeUtc;
    public String Title;
    public String Locationid;

    public Event(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getLocationid() {
        return this.Locationid;
    }

    public String getStartTimeUtc() {
        return this.StartTimeUtc;
    }

    public String getEndTimeUtc() {
        return this.EndTimeUtc;
    }

    public String getTitle() {
        return this.Title;
    }

    public Event FromJson(JSONObject object) throws JSONException {

        this.StartTimeUtc = object.getString("StartTimeUtc");
        this.EndTimeUtc = object.getString("EndTimeUtc");
        this.Title = object.getString("Title");
        this.Locationid = object.getString("LocationId");

        return this;

    }

    @Override
    public String toString() {
        return  "Title: " + Title + "\n" +
                "Start Time: " + this.StartTimeUtc + "\n" +
                "End Time: " + this.EndTimeUtc + "\n";

    }
}
