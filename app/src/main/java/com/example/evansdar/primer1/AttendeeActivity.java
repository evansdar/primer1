package com.example.evansdar.primer1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Attendee;
import com.example.evansdar.primer1.Models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AttendeeActivity extends Activity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;

    @Override
    public void onResult(JSONArray array) {

        ArrayList<Attendee> attendees = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                attendees.add(new Attendee((JSONObject) array.get(i)));
            }


            ArrayAdapter<Attendee> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, attendees);
            listView = (ListView) findViewById(R.id.displayEvents);
            listView.setAdapter(adapter);

            TextView textView = new TextView(this);
            textView.setText("Attendees");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
            listView.addHeaderView(textView);

        } catch (NullPointerException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Request cannot be completed", duration);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
    }

    @Override
    public void onResume() {
        super.onResume();

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/attendees/");

    }
}
