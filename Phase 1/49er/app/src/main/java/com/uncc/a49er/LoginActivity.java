package com.uncc.a49er;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button signup;
    Button login;
    TextInputEditText username;
    TextInputEditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
    }

    protected void findViews(){
        username=findViewById(R.id.usernameText);
        password=findViewById(R.id.passwordText);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.SignUp);

        setUpButtonListeners();
    }

    protected void setUpButtonListeners(){

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String task = "userLogin";
                BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
//                username.setText("");
//                password.setText("");
                backgroundTask.execute(task,user,pass);
            }
        });
    }
}