package com.inkd.auth.service.docusign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.docusign.*;
import com.inkd.auth.model.dto.pdf.PdfFileSignInRQ;
import com.inkd.auth.service.docusign.support.DocuSignAuthorizeCodeGrantAccessTokenAPI;
import com.inkd.auth.service.docusign.support.DocuSignRefreshAccessTokenAPI;
import com.inkd.auth.service.docusign.support.DocuSignUserInfoAPI;
import com.inkd.auth.service.docusign.support.DocuSignCreateEnvelopeAPI;
import com.inkd.auth.utils.AppsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class DocuSignServiceImpl implements DocuSignService {

    @Value("${docusign.access.token}")
    private String accessToken;

    @Autowired
    private DocuSignUserInfoAPI dociSignUserInfoAPI;

    @Autowired
    private DocuSignCreateEnvelopeAPI docuSignCreateEnvelopeAPI;

    @Autowired
    private DocuSignRefreshAccessTokenAPI docuSignRefreshAccessTokenAPI;

    @Autowired
    private DocuSignAuthorizeCodeGrantAccessTokenAPI docuSignAuthorizeCodeGrantAccessTokenAPI;

    @Override
    public DocuSignUserInfoRS getUserInfo() throws AppsException {
        DocuSignAuthRS authRS = this.getRefreshAccessTokens();
        String resultJSON = this.dociSignUserInfoAPI.getUserInfo(authRS.getAccessToken());

        ObjectMapper objectMapper = new ObjectMapper();

        DocuSignUserInfoRS docuSignUserInfoRS = null;
        try {
            //Map JSON string to objects
            docuSignUserInfoRS = objectMapper.readValue(resultJSON, DocuSignUserInfoRS.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return docuSignUserInfoRS;
    }

    public DocuSignAuthRS getAccessTokens() throws AppsException {
        String resultJSON = this.docuSignAuthorizeCodeGrantAccessTokenAPI.getAccessTokens();

        ObjectMapper objectMapper = new ObjectMapper();

        DocuSignAuthRS docuSignAuthRS = null;
        try {
            //Map JSON string to objects
            docuSignAuthRS = objectMapper.readValue(resultJSON, DocuSignAuthRS.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return docuSignAuthRS;
    }

    public DocuSignAuthRS getRefreshAccessTokens() throws AppsException {
        String resultJSON = this.docuSignRefreshAccessTokenAPI.refreshAccessTokens(accessToken);

        ObjectMapper objectMapper = new ObjectMapper();

        DocuSignAuthRS docuSignAuthRS = null;
        try {
            //Map JSON string to objects
            docuSignAuthRS = objectMapper.readValue(resultJSON, DocuSignAuthRS.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return docuSignAuthRS;
    }

    @Override
    public void signInPDF(PdfFileSignInRQ signInRQ) throws AppsException, IOException {
        DocuSignAuthRS authRS = this.getRefreshAccessTokens();

        // FIXME: 2022-12-22 Just send a sample PDF from PC for testing
        String filePath = "/Users/gayan/Documents/Files/PDF/test.pdf";
        File file = new File(filePath);

        //Get PDF data as byte array
        // FIXME: 2022-12-22
//        String base64EncodedBytes = Base64.getEncoder().encodeToString(pdfFile.getData());
        String base64EncodedBytes = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));

        DocuSignEnvelopeRQ envelopeRQ = new DocuSignEnvelopeRQ();

        //Documents
        DocuSignDocumentsDTO document = new DocuSignDocumentsDTO();
        document.setDocumentBase64(base64EncodedBytes);
        document.setDocumentId(AppsUtils.getRandomInt());
        document.setFileExtension("pdf");
        document.setName("Sign In Document");

        //Recipients
        DocuSignRecipientDTO recipient = new DocuSignRecipientDTO();

        DocuSignSignerDTO signer = new DocuSignSignerDTO();
        // FIXME: 2022-12-22
//        signer.setEmail(user.getEmail());
        signer.setEmail("gayanviraj21@gmail.com");
        // FIXME: 2022-12-22
//        signer.setName(user.getUserName());
        signer.setName("gayan");
        signer.setRecipientId("1234");

        recipient.getSigners().add(signer);

        envelopeRQ.getDocuments().add(document);
        envelopeRQ.setEmailSubject("Simple Signing Example");
        envelopeRQ.setRecipients(recipient);
        envelopeRQ.setStatus("sent");

        this.docuSignCreateEnvelopeAPI.sendSignEnvelope(envelopeRQ, authRS.getAccessToken());
    }
}

