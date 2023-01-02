package com.inkd.auth.model.dto.docusign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocuSignEnvelopeRQ implements Serializable {

    private static final long serialVersionUID = -6071026347818236167L;

    @JsonProperty("documents")
    private List<DocuSignDocumentsDTO> documents;

    @JsonProperty("emailSubject")
    private String emailSubject;

    @JsonProperty("recipients")
    private DocuSignRecipientDTO recipients;

    @JsonProperty("status")
    private String status;

    public List<DocuSignDocumentsDTO> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<>();
        }
        return documents;
    }

    public void setDocuments(List<DocuSignDocumentsDTO> documents) {
        this.documents = documents;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public DocuSignRecipientDTO getRecipients() {
        return recipients;
    }

    public void setRecipients(DocuSignRecipientDTO recipients) {
        this.recipients = recipients;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
