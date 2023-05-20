package com.example.writenow.Write;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writenow.R;

public class Question3Fragment extends Fragment {
    private EditText userInputMajor;
    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private EditText userInputNuance;
    private Button submitButton;

    private ChatGptApiManager chatGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question3, container, false);

        userInputEditText1 = view.findViewById(R.id.editText);

        submitButton = view.findViewById(R.id.submitButton);

        WriteStudentFragment parentFragment = (WriteStudentFragment) getParentFragment();
        chatGptApiManager = parentFragment.getChatGptApiManager();
        // ChatGptApiManager 인스턴스를 WriteStudentFragment로부터 가져옴

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput1 = userInputEditText1.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: "+"\n1:"+userInput1, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("다음 입력을 가지고 대학별로 지원동기, 진로계획등을 추가하여 작성해라:"+userInput1);
            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        chatGptApiManager.sendUserInputToChatGpt(userInput,3);
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
