package com.example.writenow.Write;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

        // 저장 버튼 클릭 이벤트 처리
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 결과 텍스트를 가져와서 Firebase에 저장하는 코드 추가
                String resultText = resultEditText.getText().toString();

                // 결과를 Firebase에 저장하는 코드 작성
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("결과");
                databaseReference.setValue(resultText);
            }
        });
    }
}
