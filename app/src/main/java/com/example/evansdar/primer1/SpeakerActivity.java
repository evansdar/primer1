package com.example.evansdar.primer1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Speaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SpeakerActivity extends AppCompatActivity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;
    //region delegation

    @Override
    public void onResult(JSONArray array) {

        ArrayList<Speaker> speakers = new ArrayList<Speaker>();

        try {

            for (int i = 0; i < array.length(); i++) {
                speakers.add(new Speaker((JSONObject) array.get(i)));
            }

            ArrayAdapter<Speaker> adapter = new ArrayAdapter<Speaker>(this, R.layout.activity_listview, speakers);
            listView = (ListView) findViewById(R.id.displaySpeakers);
            listView.setAdapter(adapter);


            TextView textView = new TextView(this);
            textView.setText("Speakers");
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(36);
            listView.addHeaderView(textView);

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (NullPointerException e) {

            Toast toast = Toast.makeText(SpeakerActivity.this, "Request cannot be completed", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/speakers/");

    }
}