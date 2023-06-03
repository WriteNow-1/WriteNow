package com.example.writenow.Write;

import android.os.Bundle;

import com.example.writenow.Write.ChatGptApi.TestGptApiManager;
import com.google.android.material.tabs.TabLayout;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.writenow.R;
public class TestFragment extends Fragment {



    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TestpageAdapter pagerAdapter;
    private TestGptApiManager testGptApiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager = view.findViewById(R.id.viewPager2);

        pagerAdapter = new TestpageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        testGptApiManager = new TestGptApiManager(pagerAdapter, this);

        return view;
    }

    public TestGptApiManager getChatGptApiManager() {
        return testGptApiManager;
    }

    public void handleChatGptResponse(String response, int testNumber) {
        Log.d("ChatGptResponse", "Response12: " + response+"숫자:"+testNumber);
        pagerAdapter.setResponseText(response, testNumber);
    }
}