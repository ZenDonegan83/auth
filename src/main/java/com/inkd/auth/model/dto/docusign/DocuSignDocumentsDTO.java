package com.inkd.auth.model.dto.docusign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocuSignDocumentsDTO implements Serializable {

    private static final long serialVersionUID = -3307806429370519116L;

    @JsonProperty("documentBase64")
    private String documentBase64;

    @JsonProperty("documentId")
    private Integer documentId;

    @JsonProperty("fileExtension")
    private String fileExtension;

    @JsonProperty("name")
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDocumentBase64() {
        return documentBase64;
    }

    public void setDocumentBase64(String documentBase64) {
        this.documentBase64 = documentBase64;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
