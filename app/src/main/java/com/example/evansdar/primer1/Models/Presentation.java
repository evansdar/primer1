package com.example.evansdar.primer1.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stinsocb on 7/6/2016.
 */
public class Presentation extends JSONObject {

    public String Id;
    public String Description;
    public String FileExtention;
    public String DownloadUri;


    public Presentation(JSONObject object) {
        try {
            FromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Presentation FromJson(JSONObject object) throws JSONException {

        this.Id = (String) object.get("Id");
        this.Description = (String) object.get("Description");
        this.DownloadUri = (String) object.get("DownloadUri");
        this.FileExtention = (String) object.get("FileExtention");

        return this;
    }

    public String getDownloadUri() {
        return DownloadUri;
    }

    public String getId() {
        return Id;
    }

    public String getDescription() {
        return Description;
    }

    public String getFileExtention() {
        return FileExtention;
    }
}
