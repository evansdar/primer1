package com.example.evansdar.primer1;

import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IT3775 on 7/14/16.
 */
public class WebViewFragment extends AppCompatActivity

{
    WebView wView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewlayout);
        wView = (WebView) findViewById(R.id.webViewId);
        WebSettings webSettings = wView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wView.setWebViewClient(new WebViewClient());
        wView.loadUrl(getIntent().getExtras().getString("URL"));
    }

}

