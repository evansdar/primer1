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

public class AttendeeActivity extends Activity {


    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        try {
            JSONArray array = new RetrieveJsonArrayTask().execute("https://unosmanticoreapi.azurewebsites.net/api/attendees/").get();
            Attendee attendee = new Attendee();



            //textView(array.toString());
            ArrayList<Attendee> attendees = new ArrayList<>();

            for(int i = 0; i < array.length(); i++) {
                attendees.add(new Attendee((JSONObject) array.get(i)));
            }

            TextView textView = new TextView(this);
            textView.setText("Attendees");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
            ArrayAdapter<Attendee> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, attendees);
            ListView listView = (ListView) findViewById(R.id.displayEvents);
            listView.setAdapter(adapter);
            listView.addHeaderView(textView);

            //obj.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Request cannot be completed", duration);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

