package com.example.writenow.Home;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.writenow.R;


import android.content.Context;
import androidx.annotation.NonNull;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HomeFragment extends Fragment {

    private String readDay = null;
    private String str = null;
    private CalendarView calendarView;
    private Button saveBtn, chaBtn, delBtn;
    private TextView diaryTextView, textView2, textView3;
    private EditText contextEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        calendarView = rootView.findViewById(R.id.calendarView);
        diaryTextView = rootView.findViewById(R.id.diaryTextView);
        saveBtn = rootView.findViewById(R.id.save_Btn);
        delBtn = rootView.findViewById(R.id.del_Btn);
        chaBtn = rootView.findViewById(R.id.cha_Btn);
        textView2 = rootView.findViewById(R.id.textView20);
        contextEditText = rootView.findViewById(R.id.contextEditText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                chaBtn.setVisibility(View.INVISIBLE);
                delBtn.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                contextEditText.setText("");
                checkDay(year, month, dayOfMonth);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(readDay);
                str = contextEditText.getText().toString();
                textView2.setText(str);
                saveBtn.setVisibility(View.INVISIBLE);
                chaBtn.setVisibility(View.VISIBLE);
                delBtn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    public void checkDay(int cYear, int cMonth, int cDay) {
        readDay = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fis;

        try {
            fis = getActivity().openFileInput(readDay);
            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();
            str = new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            saveBtn.setVisibility(View.INVISIBLE);
            chaBtn.setVisibility(View.VISIBLE);
            delBtn.setVisibility(View.VISIBLE);

            chaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    saveBtn.setVisibility(View.VISIBLE);
                    chaBtn.setVisibility(View.INVISIBLE);
                    delBtn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }
            });

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    saveBtn.setVisibility(View.VISIBLE);
                    chaBtn.setVisibility(View.INVISIBLE);
                    delBtn.setVisibility(View.INVISIBLE);
                    removeDiary(readDay);
                }
            });

            if (textView2.getText() == null) {
                textView2.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                chaBtn.setVisibility(View.INVISIBLE);
                delBtn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDiary(String readDay) {
        FileOutputStream fos;
        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_PRIVATE);
            String content = "";
            fos.write((content).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDiary(String readDay) {
        FileOutputStream fos;
        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_PRIVATE);
            String content = contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
