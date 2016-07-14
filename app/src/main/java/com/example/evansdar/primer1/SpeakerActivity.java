package com.example.evansdar.primer1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Event;
import com.example.evansdar.primer1.Models.Speaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpeakerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        try {
            JSONArray array = new RetrieveJsonArrayTask().execute("https://unosmanticoreapi.azurewebsites.net/api/speakers/").get();

            Speaker speaker = new Speaker();

            ArrayList<Speaker> speakers = new ArrayList<Speaker>();

            for(int i = 0; i < array.length(); i++) {
                speakers.add(new Speaker((JSONObject) array.get(i)));
            }

            ArrayAdapter<Speaker> adapter = new ArrayAdapter<Speaker>(this, R.layout.activity_listview, speakers);
            ListView listView = (ListView) findViewById(R.id.displaySpeakers);
            listView.setAdapter(adapter);
            TextView textView = new TextView(this);
            textView.setText("Speakers");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
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
