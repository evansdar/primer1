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
        this.IsExibitor = object.getBoolean("IsExibitor");
        this.Name = (String) object.get("Name");
        this.PhoneNumber = object.getInt("PhoneNumber");
        this.State = (String) object.get("State");
        this.StreetAddress = (String) object.get("StreetAddress");
        this.Summary = (String) object.get("Summary");
        this.ZipCode = object.getInt("ZipCode");



        return this;
    }

    @Override
    public String toString() {
        //TODO There is an error with institution object from api repsonse, so once that is fixed add institution to tostring method
        return  "Name: " + this.Name + "\n" +
                "Address: " + this.StreetAddress + "\n" +
                "City: " + this.City + "\n" +
                "State: " + this.State + "\n" +
                "Zip code: " + this.ZipCode + "\n" +
                "Phone Number: "+ this.PhoneNumber + "\n" +
                "Summary: " + this.Summary;
    }
}

