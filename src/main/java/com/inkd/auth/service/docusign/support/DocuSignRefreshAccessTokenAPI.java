package com.inkd.auth.service.docusign.support;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class DocuSignRefreshAccessTokenAPI {

    private static String refreshTokenURL = "https://account-d.docusign.com/oauth/token";

    private static String encodedKeys = "Basic OGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2OjIxY2VlYTFhLTAxZDMtNDBmZC1hNTVhLWE1ZTM3YTlkZjM4MQ==";

    private static String grantType = "refresh_token";

    public String refreshAccessTokens(String token) {
        String responseBody;
        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("refresh_token", token);
        map.add("grant_type", grantType);

        //Build the request
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        //Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(
                refreshTokenURL, entity, String.class);

        //Get response body
        responseBody = response.getBody();

        return responseBody;
    }

    private HttpHeaders generateHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));

        headers.set("Authorization", encodedKeys);

        return headers;
    }
}
