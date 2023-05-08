package com.example.otpverify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] language = {"English", "Hindi", "Bengali", "German"};
    ArrayAdapter<String> adapterItems;
    Button next_btn;
    AutoCompleteTextView auto_complete_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_btn = findViewById(R.id.next_btn);
        auto_complete_txt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,language);
        auto_complete_txt.setAdapter(adapterItems);


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auto_complete_txt.getText().toString().isEmpty() || auto_complete_txt.getText().toString() == null){
                    Toast.makeText(MainActivity.this, "Select Language", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                    intent.putExtra("lan",auto_complete_txt.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }
}