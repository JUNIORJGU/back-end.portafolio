package com.gorazer.mail_service.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${RESEND_API_KEY}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();

    public void sendSimpleEmail(String to, String subject, String body) throws Exception {

        String json = """
        {
          "from": "Portfolio <onboarding@resend.dev>",
          "to": ["%s"],
          "subject": "%s",
          "text": "%s"
        }
        """.formatted(to, subject, body);

        Request request = new Request.Builder()
                .url("https://api.resend.com/emails")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new RuntimeException("Error enviando email: " + response.body().string());
        }
    }
}