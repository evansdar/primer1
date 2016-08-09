package com.example.evansdar.primer1.Activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evansdar.primer1.Adapters.EventAdapter;
import com.example.evansdar.primer1.Models.Event;
import com.example.evansdar.primer1.R;
import com.example.evansdar.primer1.ResultCallback;
import com.example.evansdar.primer1.RetrieveJsonArrayTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AgendaActivity extends Activity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;
    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_agenda);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/events/");
    }


    @Override
    public void onResult(JSONArray array) {

        events = new ArrayList<>();

        try {

            for (int i = 0; i < array.length(); i++) {
                events.add(new Event((JSONObject) array.get(i)));
            }

            EventAdapter eventAdapter = new EventAdapter(this, events);
            listView = (ListView) findViewById(R.id.displayEvents);
            listView.setAdapter(eventAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Request cannot be completed", duration);
            toast.show();
        }
    }
}

