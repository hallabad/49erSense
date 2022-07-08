package com.uncc.a49er;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask  extends AsyncTask<String,Void,String> {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    String flag1;

    //Contstructor
    // Used to get the context of the activity
    BackgroundTask(Context ctx){
        this.context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        preferences= context.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE);
        editor=preferences.edit();
        editor.putString("flag","0");
        editor.commit();

        String urlLogin="http://172.73.0.183/app/login.php";
        String urlRegister="http://172.73.0.183/app/register.php";
        String urlGetLights="http://172.73.0.183/app/Lights.php";


        String task= params[0];
        flag1=task;

        //Code for userLogin
        //Connect to server/php file
        //Uses HTTP protocol
        //Create a HTTP connection and set parameters
        //Create a OutputStream and OutputStreamWriter to write and send data
        //Create a BufferWriter to write OutputStream on the buffer
        //ENCODED the data to UTF-8 format so that it can be cross compatible between different platforms

        if(task.equals("userLogin")){
            final String username= params[1];
            String password = params[2];

            try{
                URL url = new URL(urlLogin);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                //Get information from database to see if the user is validated
                //Create an InputStream and InputStreamReader to read and receive data
                //BufferReader to read the data from buffer and write to inputstream
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                StringBuilder result=new StringBuilder();
                String inputLine;
                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","userlogin");
                editor.commit();
                return (dataresponse);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }

        //Code for registerUser
        if(task.equals("registeruser")){
            final String username1=params[1];
            final String password1=params[2];
            final String emailid1=params[3];
            final String phone1 =params[4];

            try {
                URL url= new URL(urlRegister);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username1,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password1,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(emailid1,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone1,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                InputStream inputStream =httpURLConnection.getInputStream();
                inputStream.close();
                editor.putString("flag","registeruser");
                editor.commit();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if (task.equals("getLight")){
//            try {
//                URL url= new URL(urlGetLights);
//                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                OutputStream outputStream= httpURLConnection.getOutputStream() ;
//                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
//                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//                bufferedWriter.flush();
//                InputStream inputStream =httpURLConnection.getInputStream();
//                inputStream.close();
//                editor.putString("flag","getLight");
//                Intent intent = new Intent(context, GarageActivity.class);
//                context.startActivity(intent);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return null;
        }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        String flag = preferences.getString("flag","0");
//        if(flag.equals("getLight")){
//            Toast.makeText(context,"We made it to post",Toast.LENGTH_LONG).show();
//        }
        if(flag.equals("registeruser")){
            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
        if(flag.equals("userlogin")){
            String test ="false";
            String username;
            String password;
            String emailid;
            String phone;
            Toast.makeText(context,s,Toast.LENGTH_LONG).show();
            String[] serverResponse = s.split("[,]");
            test=serverResponse[0];
            if(test.equals("true")){
                username = serverResponse[1];
                password =serverResponse[2];
                emailid = serverResponse[3];
                phone=serverResponse[4];
                editor.putString("username",username);
                editor.commit();
                editor.putString("password",password);
                editor.commit();
                editor.putString("emailid",emailid);
                editor.commit();
                editor.putString("phone",phone);
                editor.commit();
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            }
            else{
                Toast.makeText(context,"Login Unsuccessful Please check username or password and try again",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
