package com.inkd.auth.service.docusign.support;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class DocuSignUserInfoAPI {
    private static String userInfoURL = "https://account-d.docusign.com/oauth/userinfo";

    public String getUserInfo(String accessToken) {
        String responseBody;

        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders(accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                userInfoURL, HttpMethod.GET, requestEntity, String.class);

        responseBody = response.getBody();

        return responseBody;
    }

    private HttpHeaders generateHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer".concat(" ").concat(accessToken));

        return headers;
    }
}
