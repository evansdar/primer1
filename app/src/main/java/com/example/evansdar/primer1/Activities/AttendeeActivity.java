package com.example.evansdar.primer1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evansdar.primer1.Adapters.AttendeeAdapter;
import com.example.evansdar.primer1.Models.Attendee;
import com.example.evansdar.primer1.R;
import com.example.evansdar.primer1.ResultCallback;
import com.example.evansdar.primer1.RetrieveJsonArrayTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttendeeActivity extends Activity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;

    @Override
    public void onResult(JSONArray array) {

        final ArrayList<Attendee> attendees = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                attendees.add(new Attendee((JSONObject) array.get(i)));
            }


            AttendeeAdapter adapter = new AttendeeAdapter(this, attendees);
            listView = (ListView) findViewById(R.id.displayAttendees);
            listView.setAdapter(adapter);


        } catch (NullPointerException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Request cannot be completed", duration);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(AttendeeActivity.this, ScrollingActivity.class);
                        intent.putExtra("Name", attendees.get(position).getFirstName() + " " + attendees.get(position).getLastName());
                        intent.putExtra("Email", attendees.get(position).getEmail());
                        startActivity(intent);

                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_attendee);
    }

    @Override
    public void onResume() {
        super.onResume();

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/attendees/");

    }
}
