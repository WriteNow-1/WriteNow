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


public class Test2Fragment extends Fragment {

    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private Button submitButton;

    private TestGptApiManager testGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        userInputEditText1 = view.findViewById(R.id.edtText4);
        userInputEditText2 = view.findViewById(R.id.edtText5);
        submitButton = view.findViewById(R.id.submitButton);

        TestFragment parentFragment = (TestFragment) getParentFragment();
        testGptApiManager = parentFragment.getChatGptApiManager();
        // ChatGptApiManager 인스턴스를 WriteStudentFragment로부터 가져옴

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput1 = userInputEditText1.getText().toString();
                String userInput2 = userInputEditText2.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: "+"\n1"+userInput1+"\n2:"+userInput2+"\n2:"+userInput2, Toast.LENGTH_SHORT).show();
                sendUserInputToChatGpt("타인과 공동체를 위해 노력한 경험은 다음과 같다:"+userInput1
                        +"그리고 배운 점은 다음과 같다:"+userInput2
                        + "이를 가지고 고등학교 재학 기간 중 타인과 공동체를 위해 노력한 경험이나 이를 통해 배운 점을 기술해달라");
            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        testGptApiManager.sendUserInputToChatGpt(userInput,2);
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