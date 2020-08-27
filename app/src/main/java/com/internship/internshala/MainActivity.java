package com.internship.internshala;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    EditText name,emailId, address, phone, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init the EditText and Button
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone=(EditText) findViewById(R.id.phone);
        emailId = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signUp);
        // implement setOnClickListener event on sign up Button
        
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate the fields and call sign method to implement the api
                if (validateName(name) && validateAdress(address) && validateEmail() && validatePhone(phone)&& isValidPassword(password)) {
                    insertUser();
                }
            }
        });
    }

    private void insertUser(){
        RestAdapter adapter= new RestAdapter.Builder().setEndpoint("file:///C:/xampp/newxampp/htdocs/").build();
        RegisterAPI api= adapter.create(RegisterAPI.class);
        api.insertUser(name.getText().toString(),address.getText().toString(),emailId.getText().toString(),
                phone.getText().toString(),password.getText().toString(),
                new Callback<Response>(){

                    @Override
                    public void success(Response response, Response response2) {
                        BufferedReader reader=null;
                        String output="";
                        try{
                            reader=new BufferedReader(new InputStreamReader(response.getBody().in()));
                            output=reader.readLine();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this,output,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();

                    }
                }



        );
        

    }

    private boolean validateEmail() {
        String email = emailId.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailId.setError("Email is not valid.");
            emailId.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validateName(EditText editText) {
        // check the length of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 4) {
            return true; // returns true if field is not empty
        }
        editText.setError("Insert more characters");
        editText.requestFocus();
        return false;
    }

    private boolean validateAdress(EditText editText) {
        // check the length of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 10) {
            return true; // returns true if field is not empty
        }
        editText.setError("Insert more characters");
        editText.requestFocus();
        return false;
    }

    private boolean validatePhone(EditText editText) {
        // check the length of the enter data in EditText and give error if its empty
        String regexStr = "^[+]?[0-9]{10,15}$";
        String number=editText.getText().toString().trim();
        if (editText.getText().toString().trim().length() > 10 && number.matches(regexStr)==true) {
            return true; // returns true if field is not empty
        }
        editText.setError("Enter valid phone number with country code");
        editText.requestFocus();
        return false;
    }

    public static boolean isValidPassword(EditText editText) {

        final String password=editText.getText().toString().trim();

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }




    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText) {
        // check the length of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }




}
