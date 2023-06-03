package com.example.writenow.Write;

import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.content.Context;
import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writenow.R;

public class Test1Fragment extends Fragment {

    private EditText userInputMajor;
    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private EditText userInputEditText3;
    private Button submitButton;

    private TestGptApiManager testGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        userInputEditText1 = view.findViewById(R.id.edtText1);
        userInputEditText2 = view.findViewById(R.id.edtText2);
        userInputEditText3 = view.findViewById(R.id.edtText3);
        submitButton = view.findViewById(R.id.submitButton);

        TestFragment parentFragment = (TestFragment) getParentFragment();
        testGptApiManager = parentFragment.getChatGptApiManager();
        // ChatGptApiManager 인스턴스를 WriteStudentFragment로부터 가져옴

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput1 = userInputEditText1.getText().toString();
                String userInput2 = userInputEditText2.getText().toString();
                String userInput3 = userInputEditText3.getText().toString();
//                Toast.makeText(getActivity(), "사용자 입력: "+"\n전공:"+userMajor+"\n1:"+userInput1+"\n2:"+userInput2, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("너는 좋은 assistant야" + userInput1 + userInput2 +
                        "자기소개서 작성을 도와줬으면 좋겠어. 자기소개서 질문은" + userInput3 + "야." +
                        "내가 이 문항에 답하기 위해 필요한 키워드 4가지를 배열 코드로 작성해줘");
            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        if (testGptApiManager != null) {
            testGptApiManager.sendUserInputToChatGpt(userInput, 1);
        } else {
            Log.d("Question1Fragment", "chatGptApiManager가 null입니다.");
        }
    }

    public void setResponseText(String response) {
        Log.d("Question1Fragment", "받은 응답: " + response);

        if (getActivity() != null) {
            Log.d("Question1Fragment", "액티비티가 null이 아닙니다");

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                    // ResultActivity를 호출하고 결과를 전달
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra("result", response);
                    startActivity(intent);
                }
            });

        } else {
            Log.d("Question1Fragment", "액티비티가 null입니다");
        }
    }

}