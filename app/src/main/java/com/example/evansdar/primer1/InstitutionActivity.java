package com.example.evansdar.primer1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Models.Institution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstitutionActivity extends Activity implements ResultCallback {


    @Override
    public void onResult(JSONArray array) {

        try {
            ArrayList<Institution> institutions = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                institutions.add(new Institution((JSONObject) array.get(i)));
            }

            ArrayAdapter<Institution> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, institutions);
            ListView listView = (ListView) findViewById(R.id.displayEvents);
            listView.setAdapter(adapter);

            TextView textView = new TextView(this);
            textView.setText("Exhibitors");
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

        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/Institutions/");
    }
}