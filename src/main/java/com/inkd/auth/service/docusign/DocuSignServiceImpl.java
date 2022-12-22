package com.inkd.auth.service.docusign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.pdf.PdfFile;
import com.inkd.auth.model.dto.docusign.*;
import com.inkd.auth.model.dto.pdf.PdfFileSignInRQ;
import com.inkd.auth.repository.pdf.PdfRepository;
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

    @Autowired
    private PdfRepository pdfRepository;

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
        if (signInRQ.getPdfFileID() != null) {
            if (this.pdfRepository.existsById(signInRQ.getPdfFileID())) {
                PdfFile pdfFile = this.pdfRepository.getById(signInRQ.getPdfFileID());

                DocuSignAuthRS authRS = this.getRefreshAccessTokens();

                // FIXME: 2022-12-22 Just send a sample PDF from PC for testing
                //  Pass the PDF file name and get file form s3 after completing the s3 config
                String filePath = "/Users/gayan/Documents/Files/PDF/test.pdf";
                File file = new File(filePath);

                //Get PDF data as byte array
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
                signer.setEmail(pdfFile.getEvent().getCustomer().getEmail());
                signer.setName(pdfFile.getEvent().getCustomer().getFirstName());
                signer.setRecipientId(String.valueOf(pdfFile.getEvent().getCustomer().getCustomerID()));

                recipient.getSigners().add(signer);

                envelopeRQ.getDocuments().add(document);
                envelopeRQ.setEmailSubject("Simple Signing Example");
                envelopeRQ.setRecipients(recipient);
                envelopeRQ.setStatus("sent");

                this.docuSignCreateEnvelopeAPI.sendSignEnvelope(envelopeRQ, authRS.getAccessToken());
            } else {
                throw new AppsException("PDF is not exists in system");
            }
        } else {
            throw new AppsException("PDF ID is not valid");
        }
    }
}

