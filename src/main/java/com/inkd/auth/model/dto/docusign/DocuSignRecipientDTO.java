package com.inkd.auth.model.dto.docusign;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocuSignRecipientDTO implements Serializable {

    private static final long serialVersionUID = -5921574703525279056L;

    @JsonProperty("signers")
    private List<DocuSignSignerDTO> signers;

    public List<DocuSignSignerDTO> getSigners() {
        if (signers == null) {
            signers = new ArrayList<>();
        }
        return signers;
    }

    public void setSigners(List<DocuSignSignerDTO> signers) {
        this.signers = signers;
    }
}
