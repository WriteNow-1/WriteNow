package com.example.writenow.Write;

import android.os.Bundle;

import com.example.writenow.Write.ChatGptApi.ChatGptApiManager;
import com.google.android.material.tabs.TabLayout;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.writenow.R;

public class WriteStudentFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private QuestionpageAdapter pagerAdapter;
    private ChatGptApiManager chatGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_student, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        pagerAdapter = new QuestionpageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        chatGptApiManager = new ChatGptApiManager(pagerAdapter, this);

        return view;
    }

    public ChatGptApiManager getChatGptApiManager() {
        return chatGptApiManager;
    }

    public void handleChatGptResponse(String response, int questionNumber) {
        Log.d("ChatGptResponse", "Response12: " + response+"숫자:"+questionNumber);
        pagerAdapter.setResponseText(response, questionNumber);
    }



}
