package com.example.evansdar.primer1.Models;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stinsocb on 7/6/2016.
 */
public class Institution {

    public String City;
    public String Id;
    public boolean IsExibitor;
    public String Name;
    public int PhoneNumber;
    public String State;
    public String StreetAddress;
    public String Summary;
    public int ZipCode;

    public Institution() {
    }

    public Institution(JSONObject object) {
        try {
            this.FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Institution FromJson(JSONObject object) throws JSONException {

        this.City = (String) object.get("City");
        this.Id = (String) object.get("Id");
        this.IsExibitor = (boolean) object.getBoolean("IsExibitor");
        this.Name = (String) object.get("Name");
        this.PhoneNumber = (int) object.getInt("PhoneNumber");
        this.State = (String) object.get("State");
        this.StreetAddress = (String) object.get("StreetAddress");
        this.Summary = (String) object.get("Summary");
        this.ZipCode = (int) object.getInt("ZipCode");



        return this;
    }
}

