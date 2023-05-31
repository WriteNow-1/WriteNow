package com.example.writenow.Write;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writenow.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.text.Editable;
import android.text.TextWatcher;

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

        if (result == null){
            String response = getIntent().getStringExtra("response");
            resultEditText.setText(response);
        }


        // 저장 버튼 클릭 이벤트 처리
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupDialog();
            }
        });

        Button cancelButton = findViewById(R.id.CancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button changeButton = findViewById(R.id.ChangeButton);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePopupDialog();
            }
        });
    }


    private void showPopupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_save, null);
        builder.setView(dialogView);

        final EditText universityEditText = dialogView.findViewById(R.id.universityEditText);
        final EditText questionNumberEditText = dialogView.findViewById(R.id.questionNumberEditText);
        Button saveButton = dialogView.findViewById(R.id.saveButton);
        Button cancelButton = dialogView.findViewById(R.id.CancelButton);

        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 대학교 이름과 문항 번호 가져오기
                String university = universityEditText.getText().toString();
                String questionNumber = questionNumberEditText.getText().toString();

                // EditText에서 결과 텍스트를 가져옴
                String resultText = resultEditText.getText().toString();

                // 결과를 Firebase에 저장하는 코드 작성
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("결과");
                DatabaseReference universityRef = databaseReference.child(university);
                universityRef.child(questionNumber).child("결과").setValue(resultText);

                Toast.makeText(ResultActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void ChangePopupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_change, null);
        builder.setView(dialogView);

        final EditText changeEditText = dialogView.findViewById(R.id.ChangeEditText);
        Button saveButton = dialogView.findViewById(R.id.ChangeButton);
        Button cancelButton = dialogView.findViewById(R.id.CancelButton);

        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정 요청 사항 받아오기
                String change = changeEditText.getText().toString();

                // EditText에서 결과 텍스트를 가져옴
                String resultText = resultEditText.getText().toString();


                Toast.makeText(ResultActivity.this, "수정되었습니다.", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }




}
