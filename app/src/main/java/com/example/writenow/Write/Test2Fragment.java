package com.example.writenow.Write;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writenow.R;
import com.example.writenow.Write.ChatGptApi.TestGptApiManager;


public class Test2Fragment extends Fragment {

    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private EditText userInputEditText3;
    private Button submitButton;

    private TestGptApiManager testGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        userInputEditText1 = view.findViewById(R.id.edtText4);
        userInputEditText2 = view.findViewById(R.id.edtText5);
        userInputEditText3 = view.findViewById(R.id.edtText6);
        submitButton = view.findViewById(R.id.submitButton);

        TestFragment parentFragment = (TestFragment) getParentFragment();
        testGptApiManager = parentFragment.getChatGptApiManager();
        // ChatGptApiManager 인스턴스를 TestFragment로부터 가져옴



        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput1 = userInputEditText1.getText().toString();
                String userInput2 = userInputEditText2.getText().toString();
                String userInput3 = userInputEditText3.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력을 바탕으로 생성 중", Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("너는 좋은 assistant야" + userInput1 + "에 지원을 하려고 해" +
                        "자기소개서 작성을 도와줬으면 좋겠어. 자기소개서 상세 질문은" + userInput2 + "야." +
                        "이 질문에 대한 나의 정보는" + userInput3 + ". 내가 준 정보들로만 구성하여 작성을 부탁해");
            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        testGptApiManager.sendUserInputToChatGpt(userInput,2);
    }

    public void setResponseText(String response) {
        Log.d("Test2Fragment", "받은 응답: " + response);

        if (getActivity() != null) {
            Log.d("Test2Fragment", "액티비티가 null이 아닙니다");

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                     Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                    // ResultActivity를 호출하고 결과를 전달
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra("result", response);
                    startActivity(intent);
                }
            });

        } else {
            Log.d("Test2Fragment", "액티비티가 null입니다");
        }
    }

}