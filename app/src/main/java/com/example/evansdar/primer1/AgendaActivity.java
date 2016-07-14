/*package com.example.evansdar.primer1;

import android.widget.ListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.evansdar.primer1.Models.AgendaEvent;

import org.json.JSONArray;

import android.widget.Toast;
import com.example.evansdar.primer1.Models.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AgendaActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {`
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);


        try {
            JSONArray array = new RetrieveJsonArrayTask().execute("https://unosmanticoreapi.azurewebsites.net/api/events/").get();
            Event event = new Event();
            event.FromJson((JSONObject) array.get(0));


            //textView(array.toString());
            ArrayList<String> values = new ArrayList<String>();

        ArrayAdapter<AgendaEvent> adapter = new ArrayAdapter<AgendaEvent>(this, R.layout.activity_listview, (AgendaEvent[]) values.toArray());
        ListView listView = (ListView) findViewById(R.id.displayEvents);
        listView.setAdapter(adapter);
            for(int i = 0; i < array.length(); i++) {
                values.add( array.get(i).toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_listview, values);
            listView.setAdapter(adapter);

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
*/
