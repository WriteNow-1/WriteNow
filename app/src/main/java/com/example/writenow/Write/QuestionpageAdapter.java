// QuestionpageAdapter.java

package com.example.writenow.Write;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class QuestionpageAdapter extends FragmentPagerAdapter {
    private final int NUM_TABS = 3;
    private Question1Fragment question1Fragment;
    private Question2Fragment question2Fragment;
    private Question3Fragment question3Fragment;

    public QuestionpageAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        question1Fragment = new Question1Fragment();
        question2Fragment = new Question2Fragment();
        question3Fragment = new Question3Fragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return question1Fragment;
            case 1:
                return question2Fragment;
            case 2:
                return question3Fragment;
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
                return "자율문항";
            default:
                return null;
        }
    }

    public Fragment getQuestionFragment(int questionNumber) {
        switch (questionNumber) {
            case 1:
                Log.d("QuestionFragment", "Returning Question1Fragment");
                return question1Fragment;
            case 2:
                Log.d("QuestionFragment", "Returning Question2Fragment");
                return question2Fragment;
            case 3:
                Log.d("QuestionFragment", "Returning Question3Fragment");
                return question3Fragment;
            default:
                Log.d("QuestionFragment", "Invalid questionNumber: " + questionNumber);
                return null;
        }
    }

    public void setResponseText(String response, int questionNumber) {
        Fragment questionFragment = getQuestionFragment(questionNumber);
        Log.d("ChatGptResponse", "Response123: " + response + "숫자1:" + questionNumber);
        if (questionFragment instanceof Question1Fragment) {
            Question1Fragment question1Fragment = (Question1Fragment) questionFragment;
            question1Fragment.setResponseText(response);
        } else if (questionFragment instanceof Question2Fragment) {
            Question2Fragment question2Fragment = (Question2Fragment) questionFragment;
            question2Fragment.setResponseText(response);
        } else if (questionFragment instanceof Question3Fragment) {
            Question3Fragment question3Fragment = (Question3Fragment) questionFragment;
            question3Fragment.setResponseText(response);
        } else {
            System.out.println("최적화되지 않은 Fragment입니다.");
        }
    }
}
