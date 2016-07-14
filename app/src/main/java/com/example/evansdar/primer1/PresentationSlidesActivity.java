package com.example.evansdar.primer1;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PresentationSlidesActivity extends ListActivity {

    static private String slidesURL = "https://unosmanticoreapi.azurewebsites.net/api/PresentationDocs";
    JSONArray allSlidesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_slides);

        try {
            allSlidesArray = new RetrieveJsonArrayTask().execute(slidesURL).get();
            ArrayList<String> fileDescriptions = new ArrayList<String>();
            for (int i = 0; i < allSlidesArray.length(); i ++) {
                JSONObject jsonObject = allSlidesArray.getJSONObject(i);
                fileDescriptions.add(i, jsonObject.getString("Description"));
            }

            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, fileDescriptions);
            setListAdapter(mArrayAdapter);
        } catch (Exception e) {
            String text = "Files could not be acquired.\nCheck Internet connection.";
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (allSlidesArray != null) {
            try {
                final DownloadTask downloadTask = new DownloadTask(PresentationSlidesActivity.this);
                JSONObject jsonObject = allSlidesArray.getJSONObject(position);
                String downloadLink = jsonObject.getString("DownloadUri");
                downloadTask.execute(downloadLink);
            } catch (Exception e) {
                String text = "Download failed.";
                Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
