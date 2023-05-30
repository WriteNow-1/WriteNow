package com.example.writenow.Write;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.writenow.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewQuestionFragment extends Fragment implements ChatGptResponseListener {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "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";  // ChatGPT API 키

    private EditText userInputEditText;
    private Button sendButton;

    private class ChatGptTask extends AsyncTask<String, Void, String> {

        private ChatGptResponseListener listener;

        public ChatGptTask(ChatGptResponseListener listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
            String userInput = params[0];
            String response = "";

            try {
                URL url = new URL(API_ENDPOINT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
                connection.setDoOutput(true);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("prompt", userInput);
                jsonObject.put("max_tokens", 50);  // 최대 응답 토큰 수

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(jsonObject.toString());
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                Log.d("ChatGptTask", "Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    response = stringBuilder.toString();
                } else {
                    response = "Error: " + responseCode;
                }

                connection.disconnect();
            } catch (Exception e) {
                response = "Error: " + e.getMessage();
                Log.e("ChatGptTask", "Error: " + e.getMessage());
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (listener != null) {
                listener.onChatGptResponse(response);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_question, container, false);

        userInputEditText = view.findViewById(R.id.editQuestion);
        sendButton = view.findViewById(R.id.submitButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserInputToChatGpt();
            }
        });

        return view;
    }

    private void sendUserInputToChatGpt() {
        String userInput = userInputEditText.getText().toString();

        ChatGptTask chatGptTask = new ChatGptTask(this);
        chatGptTask.execute(userInput);
    }

    @Override
    public void onChatGptResponse(String response) {
        // ChatGPT 응답 처리
        // response를 원하는 방식으로 처리하고 출력하거나 다른 동작을 수행할 수 있습니다.

        // 다른 Activity로 답변 전달
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        intent.putExtra("response", response);
        startActivity(intent);
    }
}