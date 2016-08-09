package com.example.evansdar.primer1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evansdar.primer1.Adapters.PresentationAdapter;
import com.example.evansdar.primer1.Models.Presentation;
import com.example.evansdar.primer1.R;
import com.example.evansdar.primer1.ResultCallback;
import com.example.evansdar.primer1.RetrieveJsonArrayTask;
import com.example.evansdar.primer1.WebViewFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity implements ResultCallback {

    private ArrayList<Presentation> presentation;
    private ListView listView;
    private String name, address, email, phoneNumber;

    /****
     * Method for Setting the Height of the ListView dynamically.
     * *** Hack to fix the issue of not showing all the items of the ListView
     * *** when placed inside a ScrollView
     ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        setUpActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new RetrieveJsonArrayTask(this, this).execute("https://unosmanticoreapi.azurewebsites.net/api/PresentationDocs");

    }

    @Override
    public void onResult(JSONArray array) {

        presentation = new ArrayList<>();

        try {

            for (int i = 0; i < array.length(); i++) {
                presentation.add(new Presentation((JSONObject) array.get(i)));
            }

            PresentationAdapter adapter = new PresentationAdapter(this, presentation);
            listView = (ListView) findViewById(R.id.PowerpointListView);
            listView.setAdapter(adapter);
            setListViewHeightBasedOnChildren(listView);
            listView.setOnTouchListener(new View.OnTouchListener() {
                // Setting on Touch Listener for handling the touch inside ScrollView
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // Disallow the touch request for parent scroll on touch of child view
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String wrapper = "http://docs.google.com/gview?embedded=true&url=";
                            String downloadLink = presentation.get(position).getDownloadUri();
                            String finald = wrapper + downloadLink;
                            Intent iA = new Intent(ScrollingActivity.this, WebViewFragment.class);
                            iA.putExtra("URL", finald);
                            startActivity(iA);

                        }
                    }
            );


        } catch (JSONException e) {
            e.printStackTrace();

        } catch (NullPointerException e) {

            Toast toast = Toast.makeText(ScrollingActivity.this, "Request cannot be completed", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void setUpActivity(Intent intent) {
        name = intent.getStringExtra("Name");
        if (name == null) {
            name = "First Last";
        }
        setTitle(name);
        address = intent.getStringExtra("Address");
        if (address == null) {
            address = "123 Street";
        }
        TextView addressField = (TextView) findViewById(R.id.AddressTextView);
        addressField.setText(address);
        email = intent.getStringExtra("Email");
        if (email == null) {
            email = "email@email.com";
        }
        TextView emailField = (TextView) findViewById(R.id.EmailTextView);
        emailField.setText(email);
    }


}