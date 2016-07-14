package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by stinsocb on 7/14/2016.
 */
public class EventLocation extends JSONObject{

    public String Id;
    public String Name;

    public EventLocation(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public EventLocation FromJson(JSONObject object) throws JSONException {

        this.Id = (String) object.get("Id");
        this.Name = object.getString("Name");

        return this;

    }

    @Override
    public String toString() {
        return  this.Name;
    }
}