package com.example.evansdar.primer1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class InstitutionActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        try {
            JSONArray array = new RetrieveJsonArrayTask().execute("https://unosmanticoreapi.azurewebsites.net/api/events/").get();
            Event event = new Event();


            //textView(array.toString());
            ArrayList<Event> events = new ArrayList<>();

            for(int i = 0; i < array.length(); i++) {
                events.add(new Event((JSONObject) array.get(i)));
            }

            TextView textView = new TextView(this);
            textView.setText("Events");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
            ArrayAdapter<Event> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, events);
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

