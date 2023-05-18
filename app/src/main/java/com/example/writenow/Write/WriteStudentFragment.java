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
    private EditText userInputMajor;
    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private EditText userInputNuance;
    private Button submitButton;
    private static final String TAG = "WriteStudentFragment";

    private ChatGptApiManager chatGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_student, container, false);
        userInputMajor = view.findViewById(R.id.editMajor);
        userInputEditText1 = view.findViewById(R.id.editText);
        userInputEditText2 = view.findViewById(R.id.editText2);
        userInputNuance = view.findViewById(R.id.editNuance);

        submitButton = view.findViewById(R.id.appCompatButton);

        chatGptApiManager = new ChatGptApiManager(this); // ChatGptApiManager 생성 시 WriteStudentFragment 참조 전달

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userMajor = userInputMajor.getText().toString();
                String userInput1 = userInputEditText1.getText().toString();
                String userInput2 = userInputEditText2.getText().toString();
                String userNuance = userInputNuance.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: "+"\n전공:"+userMajor+"\n1:"+userInput1+"\n2:"+userInput2+"\n요청사항:"+userNuance, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("지원학과:"+userMajor
                        +"다음은 진로와 관련된 학습 경험혹은 교내 활동이다:"+userInput1
                        +"그리고 배운 점은 다음과 같다:"+userInput2
                        + "이를 가지고 고등학교 재학 기간 진로와 관련하여 어떤 노력을 해왔는지 본인에게 의미 있는 학습 경험과 교내 활동을 다음과 같은 요청사항을 중심으로 기술해달라:"+ userNuance);
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
