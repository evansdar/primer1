package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stinsocb on 7/6/2016.
 */
public class Attendee {

    public Institution AttendeeInstitution;
    public String Email;
    public String FirstName;
    public String Id;
    public String InstitutionId;
    public boolean IsPresenter;
    public String LastName;

    public Attendee() {
    }

    public Attendee(JSONObject object) {
        try {
            this.FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Attendee FromJson(JSONObject object) throws JSONException {

        //Something is broken with this resource (i think)
        //this.AttendeeInstitution =  new Institution().FromJson((JSONObject) object.get("AttendeeInstitution"));
        this.Email = (String) object.get("Email");
        this.FirstName = (String) object.get("FirstName");
        this.Id = (String) object.get("Id");
        this.InstitutionId = (String) object.get("InstitutionId");
        this.IsPresenter = (boolean) object.getBoolean("IsPresenter");
        this.LastName = (String) object.get("LastName");

        return null;
    }

    @Override
    public String toString() {
        //TODO There is an error with institution object from api repsonse, so once that is fixed add institution to tostring method

        String retVal = "First Name: " + this.FirstName + "\n" +
                        "Last Name: " + this.LastName  + "\n" +
                        "email: " + this.Email;

        if(this.IsPresenter){
            retVal += "\n is a presenter";
        }


        return retVal;

    }
}
