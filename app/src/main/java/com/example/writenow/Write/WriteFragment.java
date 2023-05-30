package com.example.writenow.Write;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.writenow.R;
import com.example.writenow.Set.SetFragment;


public class WriteFragment extends Fragment {

    private final int StuFrag = 1;
    private final int NewFrag = 2;
    private final int OldFrag = 3;
    Button NewButton;
    Button StudentButton;
    Button OldButton;
    private View view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_write, container, false);

        StudentButton = view.findViewById(R.id.StudentBtn);
        NewButton = view.findViewById(R.id.NewBtn);
        OldButton = view.findViewById(R.id.OldBtn);

        StudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // WriteStudentFragment 인스턴스 생성
                WriteStudentFragment writeStudentFragment = new WriteStudentFragment();

                // Fragment 전환을 위한 FragmentTransaction 시작
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                // WriteStudentFragment를 컨테이너에 추가
                fragmentTransaction.replace(R.id.main_frame, writeStudentFragment);

                // FragmentTransaction 완료
                fragmentTransaction.commit();
            }
        });

        NewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // WriteNewFragment 인스턴스 생성
                NewQuestionFragment newQuestionFragment = new NewQuestionFragment();

                // Fragment 전환을 위한 FragmentTransaction 시작
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                // WriteNewFragment를 컨테이너에 추가
                fragmentTransaction.replace(R.id.main_frame, newQuestionFragment);

                // FragmentTransaction 완료
                fragmentTransaction.commit();
            }
        });




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();





    }
}