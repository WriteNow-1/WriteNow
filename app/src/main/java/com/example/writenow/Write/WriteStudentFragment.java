package com.example.writenow.Write;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.writenow.R;

public class WriteStudentFragment extends Fragment {
    private EditText userInputEditText;
    private Button submitButton;
    private static final String TAG = "WriteStudentFragment";

    private ChatGptApiManager chatGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_student, container, false);
        userInputEditText = view.findViewById(R.id.editText);
        submitButton = view.findViewById(R.id.appCompatButton);

        chatGptApiManager = new ChatGptApiManager(this); // ChatGptApiManager 생성 시 WriteStudentFragment 참조 전달

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput = userInputEditText.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: " + userInput, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt(userInput);
            }
        });
        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        chatGptApiManager.sendUserInputToChatGpt(userInput);
    }

    public void handleChatGptResponse(String response) {
        // 응답 처리 로직을 구현합니다.
        // ChatGPT API로부터 받은 응답(response)을 사용하여 원하는 동작을 수행합니다.

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response != null) {
                    Toast.makeText(getActivity(), "ChatGPT 응답: " + response, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "ChatGPT API 요청 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
