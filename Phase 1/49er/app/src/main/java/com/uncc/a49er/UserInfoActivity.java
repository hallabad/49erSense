package com.uncc.a49er;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity {

    EditText userData;
    EditText passData;
    EditText emailData;
    EditText phoneData;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        findViews();
    }

    protected void  findViews(){
        userData=findViewById(R.id.usernameData);
        passData=findViewById(R.id.passwordData);
        emailData=findViewById(R.id.emailData);
        phoneData=findViewById(R.id.phoneData);
        preferences=this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        String username= "Username: "+preferences.getString("username","");
        String password="Password: "+preferences.getString("password","");
        String emailid ="EmailId: "+preferences.getString("emailid","");
        String phone ="Phone: "+preferences.getString("phone","");

        userData.setText(username);
        passData.setText(password);
        emailData.setText(emailid);
        phoneData.setText(phone);
    }
}