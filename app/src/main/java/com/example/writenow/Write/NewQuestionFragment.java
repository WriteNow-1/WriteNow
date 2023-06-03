package com.example.writenow.Write;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.writenow.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewQuestionFragment extends Fragment {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "";

    private EditText userInputEditText;
    private Button sendButton;

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
        final String userInput = userInputEditText.getText().toString();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = callChatGptApi(userInput);
                handleChatGptResponse(userInput, response);
            }
        });
        thread.start();
    }

    private String callChatGptApi(String userInput) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            URL url = new URL(API_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("prompt", userInput);
            jsonObject.put("max_tokens", 50);

            String requestBody = jsonObject.toString();

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    responseBuilder.append(line);
                }
            } else {
                responseBuilder.append("Error: ").append(responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            responseBuilder.append("Error: ").append(e.getMessage());
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

    private void handleChatGptResponse(final String userInput, final String response) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                intent.putExtra("response", response);
                startActivity(intent);
            }
        });
    }
}