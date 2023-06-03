// QuestionpageAdapter.java

package com.example.writenow.Write;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TestpageAdapter extends FragmentPagerAdapter {
    private final int NUM_TABS = 3;
    private Test1Fragment test1Fragment;
    private Test2Fragment test2Fragment;
    private Test3Fragment test3Fragment;

    public TestpageAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        test1Fragment = new Test1Fragment();
        test2Fragment = new Test2Fragment();
        test3Fragment = new Test3Fragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return test1Fragment;
            case 1:
                return test2Fragment;
            case 2:
                return test3Fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "문항 1";
            case 1:
                return "문항 2";
            case 2:
                return "문항 3";
            default:
                return null;
        }
    }

    public Fragment getTestFragment(int questionNumber) {
        switch (questionNumber) {
            case 1:
                Log.d("QuestionFragment", "Returning Question1Fragment");
                return test1Fragment;
            case 2:
                Log.d("QuestionFragment", "Returning Question2Fragment");
                return test2Fragment;
            case 3:
                Log.d("QuestionFragment", "Returning Question3Fragment");
                return test3Fragment;
            default:
                Log.d("QuestionFragment", "Invalid questionNumber: " + questionNumber);
                return null;
        }
    }

    public void setResponseText(String response, int testNumber) {
        Fragment testFragment = getTestFragment(testNumber);
        Log.d("ChatGptResponse", "Response123: " + response + "숫자1:" + testNumber);
        if (testFragment instanceof Test1Fragment) {
            Test1Fragment test1Fragment = (Test1Fragment) testFragment;
            test1Fragment.setResponseText(response);
        } else if (testFragment instanceof Question2Fragment) {
            Test2Fragment test2Fragment = (Test2Fragment) testFragment;
            test2Fragment.setResponseText(response);
        } else if (testFragment instanceof Question3Fragment) {
            Test3Fragment test3Fragment = (Test3Fragment) testFragment;
            test3Fragment.setResponseText(response);
        } else {
            System.out.println("최적화되지 않은 Fragment입니다.");
        }
    }
}
