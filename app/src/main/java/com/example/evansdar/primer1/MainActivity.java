package com.example.evansdar.primer1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button agenda     = (Button)findViewById(R.id.agendaButton);
        Button ppts       = (Button)findViewById(R.id.pptsButton);
        Button attendees  = (Button)findViewById(R.id.attendeesButton);
        Button speakers   = (Button)findViewById(R.id.speakersButton);
        Button exhibitors = (Button) findViewById(R.id.exhibitorsButton);
        // fetchJSON();

    }

    public void onClickToAgenda(View v){
        System.out.println("agenda");

         Intent iA = new Intent(this, AgendaActivity.class);
         startActivity(iA);
    }

    public void onClickToAttendees(View v){

        System.out.println("attendees");
          Intent iA = new Intent(this,AttendeeActivity.class);
         startActivity(iA);
    }

    public void onClickToSpeakers(View v){
        Intent iA = new Intent(this,SpeakerActivity.class);
        startActivity(iA);
        System.out.println("speakers");
    }

    public void onClickToExhibitors(View v){
        System.out.println("exhibitors");
        Intent iA = new Intent(this,InstitutionActivity.class);
        startActivity(iA);
    }

    public void onClickToPPTs(View v){
        Intent iA = new Intent(this,PresentationSlidesActivity.class);
        startActivity(iA);
        System.out.println("ppts");
    }


//    public void fetchJSON(){
//
//
//        try {
//            JSONArray array = new RetrieveJsonArrayTask().execute("https://unosmanticoreapi.azurewebsites.net/Help/Api/GET-api-Locations").get();
//
//
//
//
//
//
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }catch(ExecutionException f){
//            f.printStackTrace();
//        }
//    }

    public String changeAgendaText(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date current = new Date();
        System.out.print(sdf.format(current));
        return "";

    }
}

