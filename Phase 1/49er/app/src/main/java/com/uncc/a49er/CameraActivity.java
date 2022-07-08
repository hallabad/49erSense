package com.uncc.a49er;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class CameraActivity extends AppCompatActivity {
    WebView webView;
    Button startVideoCapture;
    String streamURL="http://192.168.1.160:8000/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        findViews();
    }

    protected void findViews() {
        webView=findViewById(R.id.webView);
        startVideoCapture = findViewById(R.id.button7);

        setUpButtonListeners();
    }



    protected void setUpButtonListeners() {
        startVideoCapture.setOnClickListener(v -> {
            webView.loadUrl(streamURL);
            startVideoCapture.setVisibility(View.INVISIBLE);
        });

    }

}