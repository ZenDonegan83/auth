package com.inkd.auth.service.docusign.support;

import com.inkd.auth.model.dto.docusign.DocuSignEnvelopeRQ;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class DocuSignCreateEnvelopeAPI {

    private static String accessToken =
            "Bearer eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAUABwCA_65tmX3aSAgAgD_Se9x92kgCAO3mT07d9VxLomRv5y1S8YkVAAEAAAAYAAEAAAAFAAAADQAkAAAAZTRlMWIzMjQtOGZmNy00NTg2LTkwMGEtNDZiYThlYzhiOWU4IgAkAAAAZTRlMWIzMjQtOGZmNy00NTg2LTkwMGEtNDZiYThlYzhiOWU4MACAGLr8lX3aSDcAMHN9uRDBwEqeyNR6DjzRxQ.cTg-MK_jYGesAVtqFSMeUg4I54wKJQujjqKh0Cv4q3I7g-jeBUY7pJcmLDW_N-zKce4fzHp-eAk3L8DeQE9Tqm8jQOBVaOjUrkmf8Vjb6ABXUjVhuJDeo1_EkcoHzCWivl0TOOyCaNcHmLfX0a1WZa8_rGzw8sQ8qSyKUTeFr4rvQtd4jPoF9ZbWiKneEu_-wlY1OW7_d74O4AAktbMvzPgrLLT5stdejVB3ZzCKBMEjH_7-wv-kiB4s7VPBCWGBI44SJPnmLV1oMvy45tnTE1VuzW4ttkffIBifB8mfqgF7fSMpCIXlmgH0lszUE_3YXr7vlET6NXwvzmsKVDDwmw";

    private static String createEnvelopeURL = "https://demo.docusign.net/restapi/v2.1/accounts/b120bea5-1e4b-4dc9-a22c-64a0bac962e7/envelopes";

    public void sendSignEnvelope(DocuSignEnvelopeRQ envelopeRQ) {
        String responseBody;
        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders();

        //Build the request
        HttpEntity<DocuSignEnvelopeRQ> entity = new HttpEntity<>(envelopeRQ, headers);

        //Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(createEnvelopeURL, entity, String.class);

        //Get response body
        responseBody = response.getBody();

        System.out.println(responseBody);
    }

    private HttpHeaders generateHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", accessToken);

        return headers;
    }
}
