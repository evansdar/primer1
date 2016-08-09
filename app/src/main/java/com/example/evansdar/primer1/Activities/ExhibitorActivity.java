package com.example.evansdar.primer1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evansdar.primer1.Adapters.ExhibitorAdapter;
import com.example.evansdar.primer1.Models.Institution;
import com.example.evansdar.primer1.R;
import com.example.evansdar.primer1.ResultCallback;
import com.example.evansdar.primer1.RetrieveJsonArrayTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExhibitorActivity extends Activity implements ResultCallback {

    private ListView listView;
    private ArrayList<Institution> exhibitors;
    private ExhibitorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_exhibitor);
    }

    @Override
    public void onResult(JSONArray array) {

        try {
            exhibitors = new ArrayList<>();

            for (int i = 0; i < array.length(); i++)
                exhibitors.add(new Institution((JSONObject) array.get(i)));

            adapter = new ExhibitorAdapter(this, exhibitors);
            listView = (ListView) findViewById(R.id.displayInstitutions);
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
                        Intent intent = new Intent(ExhibitorActivity.this, ScrollingActivity.class);
                        intent.putExtra("Name", exhibitors.get(position).getName());
                        intent.putExtra("Address", exhibitors.get(position).getCity() + ", " + exhibitors.get(position).getState());
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/Institutions/");
    }
}