package com.example.hockeyshop.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SmsApiWorker {

    private RestTemplate restTemplate;

    public void send(String phoneNumber, String smsCode) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "cf04c8b9896e1a43786ee48eb158db4586f95f8c96fb5f53");
        headers.add("content-type", "application/json");

        phoneNumber = phoneNumber.substring(1);

        String body = String.format("{\n" +
                "  \"sender\": \"SMS4B-Test\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"number\": \"%s\",\n" +
                "      \"text\": \"%s\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", phoneNumber, smsCode);

        String url = "https://api.sms4b.ru/v1/sms";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
