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

public class Question1Fragment extends Fragment {
    private EditText userInputMajor;
    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private Button submitButton;

    private ChatGptApiManager chatGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question1, container, false);
        userInputMajor = view.findViewById(R.id.editMajor);
        userInputEditText1 = view.findViewById(R.id.editText);
        userInputEditText2 = view.findViewById(R.id.editText2);
        submitButton = view.findViewById(R.id.submitButton);

        WriteStudentFragment parentFragment = (WriteStudentFragment) getParentFragment();
        chatGptApiManager = parentFragment.getChatGptApiManager();
        // ChatGptApiManager 인스턴스를 WriteStudentFragment로부터 가져옴

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userMajor = userInputMajor.getText().toString();
                String userInput1 = userInputEditText1.getText().toString();
                String userInput2 = userInputEditText2.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: "+"\n전공:"+userMajor+"\n1:"+userInput1+"\n2:"+userInput2, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("지원학과:"+userMajor
                        +"다음은 진로와 관련된 학습 경험혹은 교내 활동이다:"+userInput1
                        +"그리고 배운 점은 다음과 같다:"+userInput2
                        + "이를 가지고 고등학교 재학 기간 진로와 관련하여 어떤 노력을 해왔는지 본인에게 의미 있는 학습 경험과 교내 활동을 기술해달라:");

            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        if (chatGptApiManager != null) {
            chatGptApiManager.sendUserInputToChatGpt(userInput, 1);
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
