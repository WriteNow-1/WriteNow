package com.example.writenow.Write;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import com.example.writenow.R;

public class ResultActivity extends AppCompatActivity {
    private EditText resultEditText;
    private TextView characterCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultEditText = findViewById(R.id.resultEditText);
        characterCountTextView = findViewById(R.id.characterCountTextView);

        resultEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 이 메서드를 구현하지 않아도 됩니다.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 이 메서드를 구현하지 않아도 됩니다.
            }

            @Override
            public void afterTextChanged(Editable s) {
                int characterCount = s.toString().length();
                characterCountTextView.setText("글자 수: " + characterCount);
            }
        });

        // API로부터 받아온 결과를 편집기에 설정
        String result = getIntent().getStringExtra("result");
        resultEditText.setText(result);
    }
}
