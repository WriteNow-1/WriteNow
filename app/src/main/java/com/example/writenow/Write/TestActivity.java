package com.example.writenow.Write;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writenow.R;


public class TestActivity extends AppCompatActivity {


    private EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        resultEditText = findViewById(R.id.resultEditText);

        String response = getIntent().getStringExtra("response");
        resultEditText.setText(response);

    }
}
