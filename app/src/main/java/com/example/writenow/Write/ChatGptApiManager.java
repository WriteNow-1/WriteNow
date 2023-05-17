package com.example.writenow.Write;
import android.util.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
public class ChatGptApiManager {
    private static final String TAG = "ChatGptApiManager";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static String getChatGptResponse(String userInput) {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_input", userInput);
            String requestBody = jsonObject.toString();

            RequestBody body = RequestBody.create(mediaType, requestBody);

            Request request = new Request.Builder()
                    .url("https://api.openai.com/v1/chat/completions")
                    .post(body)
                    .addHeader("Authorization", "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")
                    .build();

            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                String responseString = responseBody.string();
                JSONObject jsonResponse = new JSONObject(responseString);
                String chatGptResponse = jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getString("text");

                return chatGptResponse;
            }
        } catch (Exception e) {
            Log.e(TAG, "ChatGPT API request failed", e);
        }

        return null;
    }
}
