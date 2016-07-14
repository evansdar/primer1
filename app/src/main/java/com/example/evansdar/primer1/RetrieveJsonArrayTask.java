package com.example.evansdar.primer1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.DialerFilter;
import android.widget.ResourceCursorTreeAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class RetrieveJsonArrayTask extends AsyncTask<String, Void, JSONArray> {
    private Exception exception;
    private Context context;
    private Dialog dlg;
    private ResultCallback call;

    public RetrieveJsonArrayTask(Context context, ResultCallback call)
    {
        this.context = context;
        dlg = new ProgressDialog(context);
        this.call = call;
    }


    @Override
    protected void onPreExecute()
    {
        if(!dlg.isShowing())dlg.show();
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        try {
            //URL url = new URL(urls[0]);
            JSONArray obj = this.readJsonArrayFromUrl(params[0]);

            return obj;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }
    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        if(dlg.isShowing())dlg.dismiss();
        call.onResult(result);
        //return result;
    }
    public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}