package com.uncc.a49er;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GarageActivity extends AppCompatActivity {
    private WebView webView;
    String streamURL="http://172.73.0.183/app/empty.php";
    TextView g1state, g2state;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public void gbopen1(View view) {
        // Do something in response to button
        g1state.setText("Opened");
        String task = "open";
        webView.loadUrl("http://172.73.0.183/App/setdoor.php?col=Open&val=0&ID=GarageDoor+1");
//        AsyncRetrieve tasking = new AsyncRetrieve();
//        tasking.execute(task);
    }
    public void gbclose1(View view) {
        // Do something in response to button
        g1state.setText("Closed");
        webView.loadUrl("http://172.73.0.183/App/setdoor.php?col=Open&val=1&ID=GarageDoor+1");

    }
    public void gbopen2(View view) {
        // Do something in response to button
        g2state.setText("Opened");
        String task = "open";
        webView.loadUrl("http://172.73.0.183/App/setdoor.php?col=Open&val=0&ID=GarageDoor+2");
    }
    public void gbclose2(View view) {
        // Do something in response to button
        g2state.setText("Closed");
        webView.loadUrl("http://172.73.0.183/App/setdoor.php?col=Open&val=1&ID=GarageDoor+2");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        g1state = (TextView) findViewById(R.id.textView12);
        g2state = (TextView) findViewById(R.id.textView16);
        webView= (WebView) findViewById(R.id.cWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(streamURL);
        //Make call to AsyncRetrieve
        new AsyncRetrieve().execute();

    }


    private class AsyncRetrieve extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(GarageActivity.this);
        HttpURLConnection conn;
        URL url = null;

        //this method will interact with UI, here display loading message
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        // This method does not interact with UI, You need to pass result to onPostExecute to display
        @Override
        protected String doInBackground(String... params) {
            String stringResult;
            try {
                // Enter URL address where your php file resides
                url = new URL("http://172.73.0.183/App/getgarage.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    stringResult= result.toString();
                    //Here we try to update
//                    try {
//                        if(params[0]=="open"){
//                            URL url= new URL("http://172.73.0.183/app/setgarage.php");
//                            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
//                            httpURLConnection.setRequestMethod("GET");
//                            httpURLConnection.setDoInput(true);
//
//                        }
//                        else if(params[0]=="close"){
//
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                    // Pass data to onPostExecute method
                    return (stringResult);

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        // this method will interact with UI, display result sent from doInBackground method
        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();
            String s;
            try {
                String[] response = result.split("[,]");
//            if(result.equals("Success! This message is from PHP")) {
                g1state.setText(response[0].toString());
                g2state.setText(response[1].toString());
//            }else{
//                // you to understand error returned from doInBackground method
//                Toast.makeText(GarageActivity.this, result.toString(), Toast.LENGTH_LONG).show();
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}