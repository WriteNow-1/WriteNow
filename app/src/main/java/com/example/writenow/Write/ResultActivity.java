package com.example.writenow.Write;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.EditText;

import android.os.Bundle;
import com.example.writenow.R;

public class ResultActivity extends AppCompatActivity {
    private EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultEditText = findViewById(R.id.resultEditText);

        // API로부터 받아온 결과를 편집기에 설정
        String result = getIntent().getStringExtra("result");
        resultEditText.setText(result);
    }
}
