package com.uncc.a49er;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.util.ArrayList;

public class LightsOnePageActivity extends AppCompatActivity {

    private WebView webView;
    String streamURL="http://172.73.0.183/App/Lights.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_one_page);
        webView= (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(streamURL);

//        for(int i=0; i < serverResponse.length;i++){
//            LightsDTO lights = new LightsDTO();
//            lights.setLightID(serverResponse[i]);
//            if(serverResponse[i+1].equalsIgnoreCase("1")){
//                lights.setStatus(true);
//            }else{
//                lights.setStatus(false);
//            }
//            lights.setDimmerLevel(serverResponse[i+2]);
//            i=i+2;
//            ls.add(lights);
//        }

    }
}