package com.uncc.a49er;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;

public class HomeActivity extends AppCompatActivity {
//    Context context;
//    HomeActivity(Context ctx2){
//        this.context=ctx2;
//    }
    TextView textView;
    private WebView webView;
    String streamURL="http://172.73.0.183/app/empty.php";

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.textViewAlarm);
        textView.setText("Not Ready");
        webView= (WebView) findViewById(R.id.cWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(streamURL);
        //Make call to AsyncRetrieve
        new AsyncRetrieve().execute();
    }
    public void setDis(View view) {
        // Do something in response to button
        textView.setText("Disarmed");

        webView.loadUrl("http://172.73.0.183/App/setsystem.php?col=Armed&val=0&sysID=System");

    }
    public void setArmA(View view) {
        // Do something in response to button
        textView.setText("Armed Away");
        webView.loadUrl("http://172.73.0.183/App/setsystem.php?col=Armed&val=2&sysID=System");

    }
    public void setArmS(View view) {
        // Do something in response to button
        textView.setText("Armed Stay");
        webView.loadUrl("http://172.73.0.183/App/setsystem.php?col=Armed&val=1&sysID=System");

    }
    public void gotoUserInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }
    public void gotoCamera(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
    public void gotoDoors(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DoorsActivity.class);
        startActivity(intent);
    }
    public void gotoMotion(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MotionActivity.class);
        startActivity(intent);
    }
    public void gotoThermo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ThermostatsActivity.class);
        startActivity(intent);
    }
    public void gotoLight(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LightsOnePageActivity.class);
        startActivity(intent);
    }
//    public void gotoLight(View view) {
//        // Do something in response to button
//        String task = "getLight";
//        String fillers = "";
//        BackgroundTask backgroundTask = new BackgroundTask(HomeActivity.this);
//        backgroundTask.execute(task,fillers,fillers);
//        TextView textView = findViewById(R.id.textViewAlarm);
//        textView.setText("We Are Litt!");
//    }
//    public void gotoLight(View view) {
//        // Do something in response to button
//        String task = "lights";
//        String fillers = "";
//        BackgroundTask backgroundTask = new BackgroundTask(HomeActivity.this);
//        backgroundTask.execute(task,fillers,fillers);
//
//        Intent intent = new Intent(this, LightsOnePageActivity.class);
//        startActivity(intent);
//    }
    public void gotoLocks(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LocksActivity.class);
        startActivity(intent);
    }
    public void gotoGarage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GarageActivity.class);
        startActivity(intent);
    }
    public void gotoWeather(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
    private class AsyncRetrieve extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(HomeActivity.this);
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
                url = new URL("http://172.73.0.183/App/getSystem.php");

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
            textView.setText("We got to post");
            try {
                textView.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

}