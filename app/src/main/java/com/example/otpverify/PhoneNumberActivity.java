package com.example.otpverify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.Locale;

public class PhoneNumberActivity extends AppCompatActivity {

    Button continue_btn;
    CountryCodePicker ccp;
    EditText mobile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);


        String lan = getIntent().getStringExtra("lan");

        if(lan != null || !lan.isEmpty()){
            //change language of screen text

            if(lan.equals("English")){
                setLan("eng");
            }
            else if (lan.equals("Bengali")) {
                setLan("ben");
            }
            else if (lan.equals("Hindi")) {
                setLan("hin");
            }
            else if (lan.equals("German")) {
                setLan("deu");
            }
        }

        continue_btn = findViewById(R.id.continue_btn);
        ccp = findViewById(R.id.ccp);
        mobile = findViewById(R.id.mobile);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        ccp.registerCarrierNumberEditText(mobile);

        findViewById(R.id.cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String phn_num = mobile.getText().toString();
                if(TextUtils.isEmpty(phn_num)){
                    mobile.setError("Valid Mobile Number Required");
                }
                else{
                    if(isInternetAvailable()){
                        progressBar.setVisibility(View.GONE);
                        Intent intent=new Intent(PhoneNumberActivity.this,OtpActivity.class);
                        intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" "," "));
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(PhoneNumberActivity.this, "Enable Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    private void setLan( String lanCode) {
        Locale locale = new Locale(lanCode);
        locale.setDefault(locale);
        Resources resources = getApplicationContext().getResources();
        Configuration confiq = resources.getConfiguration();
        confiq.setLocale(locale);
        resources.updateConfiguration(confiq,resources.getDisplayMetrics());
        //startActivity(getIntent());
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}