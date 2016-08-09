package com.example.evansdar.primer1.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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


public class MainActivity extends AppCompatActivity implements ResultCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public void onBackPressed() {
        //Let the call die so user cannot
    }

    public void onClickToAgenda(View v) {
        System.out.println("agenda");

        Intent iA = new Intent(this, AgendaActivity.class);
        startActivity(iA);
    }

    public void onClickToAttendees(View v) {

        System.out.println("attendees");
        Intent iA = new Intent(this, AttendeeActivity.class);
        startActivity(iA);
    }

    public void onClickToSpeakers(View v) {
        Intent iA = new Intent(this, SpeakerActivity.class);
        startActivity(iA);
        System.out.println("speakers");
    }

    public void onClickToExhibitors(View v) {
        System.out.println("exhibitors");
        Intent iA = new Intent(this, ExhibitorActivity.class);
        startActivity(iA);
    }

    public void onClickToPPTs(View v) {
        Intent iA = new Intent(this, ScrollingActivity.class);
        startActivity(iA);
        System.out.println("ppts");
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

            ArrayList<Event> eventsforhomepage = new ArrayList<>();
            eventsforhomepage.add(events.get(0));
            eventsforhomepage.add(events.get(1));
            eventsforhomepage.add(events.get(2));


            EventAdapter adapter = new EventAdapter(this, eventsforhomepage);
            ListView listView = (ListView) findViewById(R.id.previewListView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                    startActivity(intent);

                }
            });

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

