package com.example.otpverify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RadioButton radio_btn_shipper = findViewById(R.id.radio_btn_shipper);
        radio_btn_shipper.setChecked(true);

        RadioButton radio_btn_transporter = findViewById(R.id.radio_btn_transporter);

        radio_btn_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_btn_transporter.setChecked(false);
                radio_btn_shipper.setChecked(true);
            }
        });
        radio_btn_transporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_btn_shipper.setChecked(false);
                radio_btn_transporter.setChecked(true);
            }
        });

        findViewById(R.id.profile_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Thanks for Checking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}