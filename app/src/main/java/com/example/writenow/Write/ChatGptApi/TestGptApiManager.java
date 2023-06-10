package com.example.writenow.Write.ChatGptApi;

import android.util.Log;

import com.example.writenow.Write.TestFragment;
import com.example.writenow.Write.TestpageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TestGptApiManager {
    private static final String TAG = "TestGptApiManager";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .build();
    private static final String MODEL_ID = "gpt-3.5-turbo";
    private TestpageAdapter adapter;

    private TestFragment testFragment;



    public TestGptApiManager(TestpageAdapter adapter, TestFragment testFragment) {
        this.adapter = adapter;
        this.testFragment = testFragment;
    }


    public void sendUserInputToChatGpt(String userInput, int testNumber) {
        JSONArray messagesArray = new JSONArray();

        // 사용자 입력 메시지 추가
        JSONObject userInputMessage = new JSONObject();
        try {
            userInputMessage.put("role", "system");
            userInputMessage.put("content", userInput);
            messagesArray.put(userInputMessage);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create user input message: " + e.getMessage());
            testFragment.handleChatGptResponse(null, testNumber);
            return;
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("messages", messagesArray);
            jsonObject.put("model", MODEL_ID);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSON request body: " + e.getMessage());
            testFragment.handleChatGptResponse(null, testNumber);
            return;
        }

        String requestBody = jsonObject.toString();

        RequestBody body = RequestBody.create(requestBody, JSON);

        Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .addHeader("Authorization", "Bearer " + "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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "ChatGPT API request failed: " + e.toString());
                testFragment.handleChatGptResponse(null, testNumber);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                try {
                    if (response != null) {
                        Log.d(TAG, "Response code: " + response.code());
                        Log.d(TAG, "Response: " + response.toString());
                        String responseString = responseBody.string();
                        Log.d(TAG, "Response body: " + responseString);

                        if (response.isSuccessful()) {
                            Log.d(TAG, "ChatGPT API request successful");
                            JSONObject jsonResponse = new JSONObject(responseString);

                            if (jsonResponse.has("choices")) {
                                JSONArray choicesArray = jsonResponse.getJSONArray("choices");
                                if (choicesArray.length() > 0) {
                                    JSONObject choiceObject = choicesArray.getJSONObject(0);
                                    if (choiceObject.has("message")) {
                                        JSONObject messageObject = choiceObject.getJSONObject("message");
                                        if (messageObject.has("content")) {
                                            String text = messageObject.getString("content");
                                            testFragment.handleChatGptResponse(text, testNumber);
                                            return;
                                        }
                                    }
                                }
                            }

                            Log.e(TAG, "Failed to extract data from API response");
                        } else {
                            Log.e(TAG, "ChatGPT API request failed: " + response.message());
                        }
                    } else {
                        Log.e(TAG, "ChatGPT API request failed: Response is null");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Failed to parse API response: " + e.getMessage());
                } finally {
                    if (responseBody != null) {
                        responseBody.close();
                    }
                }

                testFragment.handleChatGptResponse(null, testNumber);
            }
        });
    }


}
