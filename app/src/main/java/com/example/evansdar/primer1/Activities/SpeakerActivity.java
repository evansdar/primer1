package com.example.evansdar.primer1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evansdar.primer1.Adapters.SpeakerAdapter;
import com.example.evansdar.primer1.Models.Speaker;
import com.example.evansdar.primer1.R;
import com.example.evansdar.primer1.ResultCallback;
import com.example.evansdar.primer1.RetrieveJsonArrayTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SpeakerActivity extends AppCompatActivity implements ResultCallback {

    private ResultCallback listener;
    private ListView listView;
    private ArrayList<Speaker> speakers;

    @Override
    public void onResult(JSONArray array) {

        speakers = new ArrayList<Speaker>();

        try {

            for (int i = 0; i < array.length(); i++) {
                speakers.add(new Speaker((JSONObject) array.get(i)));
            }

            SpeakerAdapter adapter = new SpeakerAdapter(this, speakers);
            listView = (ListView) findViewById(R.id.displaySpeakers);
            listView.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();

        } catch (NullPointerException e) {

            Toast toast = Toast.makeText(SpeakerActivity.this, "Request cannot be completed", Toast.LENGTH_LONG);
            toast.show();
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SpeakerActivity.this, ScrollingActivity.class);
                        intent.putExtra("Name", speakers.get(position).getName());
                        intent.putExtra("Address", speakers.get(position).getCity() + ", " + speakers.get(position).getState());
                        intent.putExtra("Email", speakers.get(position).getEmail());
                        startActivity(intent);

                    }
                }
        );
    }

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_speaker);

    }


    @Override
    protected void onResume() {
        super.onResume();

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/speakers/");

    }
}