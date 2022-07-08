package com.uncc.a49er;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText emailid;
    TextInputEditText phone;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
    }

    protected void findViews(){
        username=findViewById(R.id.userText);
        password= findViewById(R.id.passText);
        emailid=findViewById(R.id.EmailId);
        phone=findViewById(R.id.phoneText);
        register=findViewById(R.id.regbutton);

        setUpButtonListeners();
    }

    protected void setUpButtonListeners(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1=username.getText().toString();
                String password1=password.getText().toString();
                String emailid1=emailid.getText().toString();
                String phone1=phone.getText().toString();
                String task="registeruser";

                BackgroundTask backgroundTask = new BackgroundTask(RegisterActivity.this);
                username.setText("");
                password.setText("");
                emailid.setText("");
                phone.setText("");

                backgroundTask.execute(task,username1,password1,emailid1,phone1);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}