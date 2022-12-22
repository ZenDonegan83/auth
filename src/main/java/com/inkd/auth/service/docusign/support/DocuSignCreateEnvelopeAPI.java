package com.inkd.auth.service.docusign.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inkd.auth.model.dto.docusign.DocuSignEnvelopeRQ;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class DocuSignCreateEnvelopeAPI {

    @Value("${docusign.create.envelope.url}")
    private String createEnvelopeURL;

    public void sendSignEnvelope(DocuSignEnvelopeRQ envelopeRQ, String accessToken) throws JsonProcessingException {
        String responseBody;
        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders(accessToken);

        //Build the request
        HttpEntity<DocuSignEnvelopeRQ> entity = new HttpEntity<>(envelopeRQ, headers);

        //Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(createEnvelopeURL, entity, String.class);

        //Get response body
        responseBody = response.getBody();

        System.out.println(responseBody);
    }

    private HttpHeaders generateHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer".concat(" ").concat(accessToken));

        return headers;
    }
}
