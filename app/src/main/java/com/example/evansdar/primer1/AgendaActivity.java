package com.example.evansdar.primer1;


import android.view.Gravity;
import android.widget.ListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ArrayAdapter;

import org.json.JSONArray;


import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AgendaActivity extends Activity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onResume() {

        super.onResume();

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/events/");
    }


    @Override
    public void onResult(JSONArray array) {

        ArrayList<Event> events = new ArrayList<>();

        try {

            for (int i = 0; i < array.length(); i++) {
                events.add(new Event((JSONObject) array.get(i)));
            }

            ArrayAdapter<Event> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, events);
            listView = (ListView) findViewById(R.id.displayEvents);
            listView.setAdapter(adapter);

            TextView textView = new TextView(this);
            textView.setText("Events");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
            listView.addHeaderView(textView);

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

