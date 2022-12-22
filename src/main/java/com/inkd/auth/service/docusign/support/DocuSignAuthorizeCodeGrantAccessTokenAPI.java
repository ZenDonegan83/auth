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
public class DocuSignAuthorizeCodeGrantAccessTokenAPI {

    private static String authorizeCodeGrantAccessTokenAPIURL = "https://account-d.docusign.com/oauth/token";

    private static String code = "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAYABwAAy0SQwOPaSAgAAFfL18Dj2kgCANkjenJfpnRAg7w8ya9uJkcVAAEAAAAYAAEAAAAFAAAADQAkAAAAOGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2IgAkAAAAOGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2NwAVBpJ1k8jCQZLXZm0G8dw9MACA0DbUvuPaSA.h_hsvo68IIXJx8JGdwrg-AH5BMzlDL66T0BUBOBbali42ziB0T4ssF3_aHI2ma4accQplcfGYjZ78s85DJb5W39QJaLqoDBwdCdLJuqP596qKGpbOIioZqSA-YE8VwUC9Si6zCObf8RwwMvcHbccsLz_P6CJFRjFhzI_WIXwdP45Ec-2r6_U4VfFMbqp6KS9m6oLH2is2ocq9azgUo2WbLK5Ta0fuAs0gaVtId810a8FRURUQIxGQrrZBAVATLeP3OLUlF_d8abwjng7if-2w6k-WCD0LZXkU0YvECff2hLYnrY_qpiP5ElTmaqNr0Ryl-sZSBwkqt81XGGBdv6tSA";

    private static String grantType = "authorization_code";

    private static String encodedKeys = "Basic OGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2OjIxY2VlYTFhLTAxZDMtNDBmZC1hNTVhLWE1ZTM3YTlkZjM4MQ==";

    public String getAccessTokens() {
        String responseBody;
        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("grant_type", grantType);

        //Build the request
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        //Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(
                authorizeCodeGrantAccessTokenAPIURL, entity, String.class);

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
