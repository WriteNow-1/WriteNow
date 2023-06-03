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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writenow.R;


public class Test3Fragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private RadioGroup radioGroup;
    private EditText userInputEditText1;
    private EditText userInputEditText2;
    private EditText userInputEditText3;
    private EditText userInputEditText4;
    private EditText userInputQeustion;
    private Button submitButton;

    private TestGptApiManager testGptApiManager;

    int num = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test3, container, false);

        textView1 = view.findViewById(R.id.text1);
        textView2 = view.findViewById(R.id.text2);
        textView3 = view.findViewById(R.id.text3);
        textView4 = view.findViewById(R.id.text4);
        userInputEditText1 = view.findViewById(R.id.editText1);
        userInputEditText2 = view.findViewById(R.id.editText2);
        userInputEditText3 = view.findViewById(R.id.editText3);
        userInputEditText4 = view.findViewById(R.id.editText4);
        radioGroup = view.findViewById(R.id.radioGroup);




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1) {
                    textView1.setText("지원학교와 학과");
                    userInputEditText1.setHint("지원하는 학교와 학과를 작성");
                    textView2.setText("해당 학과에 관심을 갖게한 개인적 경험 또는 성장 과정");
                    userInputEditText2.setHint("어떤 경험을 통해 그 분야에 관심을 갖게 되었는지 등을 구체적으로 기술");
                    textView3.setText("지원 대학의 교육 철학과 가치에 대한 이해");
                    userInputEditText3.setHint("대학이 강조하는 가치와 자신의 목표, 가치관이 어떻게 일치하는지를 설명");
                    textView4.setText("지원 대학의 프로그램, 시설, 리소스");
                    userInputEditText4.setHint("해당 대학이 제공하는 프로그램, 시설, 리소스에 대해 자신의 학문적 또는 개인적 성장에 어떻게 도움을 줄 것인지를 설명");
                    num = 1;
                } else if (i == R.id.radioButton2) {
                    textView1.setText("해당 학과에 대한 관심과 열정");
                    userInputEditText1.setHint("나의 관심 분야에서 어떤 준비를 했으며, 어떤 목표를 가지고 나아가고 있는지를 설명");
                    textView2.setText("해당 학과에 진학하기 위한 경험과 학습 활동");
                    userInputEditText2.setHint("경험들이 어떻게 나의 성장과 발전에 기여했는지를 설명");
                    textView3.setText("자기계발");
                    userInputEditText3.setHint("자신의 자기개발을 위해 어떤 노력을 했는지와 그 결과를 작성");
                    textView4.setText("학업적인 업적과 성취");
                    userInputEditText4.setHint("학업적으로 어떤 준비와 노력을 했으며, 어떤 목표를 이루기 위해 노력했는지를 설명");
                    num = 2;
                } else if (i == R.id.radioButton3) {
                    textView1.setText("전공 및 관심 분야 선택");
                    userInputEditText1.setHint("자신이 어떤 전공을 선택하고, 해당 분야에 대한 관심과 열정을 어떻게 가지게 되었는지 설명");
                    textView2.setText("학업목표와 목표달성 계획");
                    userInputEditText2.setHint("대학에서 어떤 학업목표를 가지고 있는지, 어떤 성적을 달성하고 어떤 학업적 성장을 이루고 싶은지를 명확하게 작성");
                    textView3.setText("학업자원의 활용");
                    userInputEditText3.setHint("대학에서 제공되는 학업자원을 어떻게 활용할 것인지를 작성");
                    textView4.setText("학습능력 향상을 위한 계획");
                    userInputEditText4.setHint("자신의 학습 능력을 향상시키기 위한 계획을 작성");
                    num = 3;
                } else if (i == R.id.radioButton4) {
                    textView1.setText("진로 목표 및 이유");
                    userInputEditText1.setHint("자신이 어떤 분야나 직업을 희망하는지 명확하게 기술");
                    textView2.setText("관련된 학습과 경험");
                    userInputEditText2.setHint("해당 분야나 직업과 관련된 학습과 경험을 언급");
                    textView3.setText("전문성과 자기계발");
                    userInputEditText3.setHint("자신의 전문성을 키우기 위해 어떤 노력을 하고 있는지를 작성");
                    textView4.setText("장기적인 목표와 계획");
                    userInputEditText4.setHint("자신의 장기적인 진로 목표와 그를 위한 계획을 작성");
                    num = 4;
                }
            }
        });




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
                String userInput4 = userInputEditText4.getText().toString();
                Toast.makeText(getActivity(), "사용자 입력: "+"\n1:"+userInput1, Toast.LENGTH_SHORT).show();

                if(num == 1){
                    sendUserInputToChatGpt("대학 진학 자소서 지원 동기를 작성해줘 지원하는 학교와 학과는" + userInput1 + "이야." +
                            "해당 학과에 관심을 갖게한 개인적 경험 또는 성장 과정은" +userInput2 +
                            "지원 대학의 교육 철학과 가치에 대한 이해를 위해서는" + userInput3 +
                            "지원 대학의 프로그램, 시설, 리소스를 활용하여" + userInput4 + "문장은 끊기지 않고 매끄럽게 이어지도록 부탁해");
                }
                else if(num == 2){
                    sendUserInputToChatGpt("대학 진학 자소서 준비 상황을 작성해줘 해당 학과에 대한 관심과 열정으로 나는" + userInput1 +
                            "해당 학과에 진학하기 위해 나는" +userInput2 +
                            "지원 대학의 교육 철학과 가치에 대한 이해를 위해는" + userInput3 +
                            "이 대학을 지원하기 위해 내가 한 준비는 " + userInput4 + "문장은 끊기지 않고 매끄럽게 이어지도록 부탁해");
                }
                else if(num == 3){
                    sendUserInputToChatGpt("대학 진학 자소서 학업 계획을 작성해줘 전공과 관심 분야를 선택한 이유는" + userInput1 +
                            "대학을 입학해서 하고 싶은 것은" +userInput2 +
                            "대학을 입학한다면" + userInput3 +
                            "학습 능력을 향싱시킬 계획으로는" + userInput4 + "문장은 끊기지 않고 매끄럽게 이어지도록 부탁해");
                }
                else if(num == 4){
                    sendUserInputToChatGpt("대학 진학 자소서 진로 계획을 작성해줘 내가 목표로 하는 진로와 이유는" + userInput1 +
                            "진로와 관련된 학습과 경험으로는" +userInput2 +
                            "진로를 위한 전문성을 갖추기 위한 자기계발로는" + userInput3 +
                            "앞으로 진로를 위한 장기적인 목표와 계획은" + userInput4 + "문장은 끊기지 않고 매끄럽게 이어지도록 부탁해");
                }

            }
        });

        return view;
    }

    private void sendUserInputToChatGpt(String userInput) {
        // ChatGptApiManager를 사용하여 사용자 입력을 ChatGPT API로 전송
        testGptApiManager.sendUserInputToChatGpt(userInput,3);
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