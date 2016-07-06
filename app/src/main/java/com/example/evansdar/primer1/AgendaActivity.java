package com.example.evansdar.primer1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AgendaActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);



        // Array of events
        AgendaEvent[] agendaEvents = new AgendaEvent[10];

        for(int i=0;i<agendaEvents.length;i++)
        {
            agendaEvents[i] = new AgendaEvent("sfuaf","jfda","agfgh","jdfjg");
        }


        ArrayAdapter<AgendaEvent> adapter = new ArrayAdapter<AgendaEvent>(this,R.layout.activity_listview,agendaEvents);
        ListView listView = (ListView) findViewById(R.id.displayEvents);
        listView.setAdapter(adapter);

        /*
        try {
            String text = parseJson().toString();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        */
    }

    public ArrayList<String> parseJson() throws Exception{


        JSONArray Jevents = new RetrieveJsonArrayTask().execute("http://unosmanticoreapi.azurewebsites.net/api/Events").get();


        ArrayList<String> events = new ArrayList<>();
        if (Jevents != null) {
            for (int i=0;i<Jevents.length();i++){

                try
                {
                    events.add(Jevents.get(i).toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return events;

    }
}

