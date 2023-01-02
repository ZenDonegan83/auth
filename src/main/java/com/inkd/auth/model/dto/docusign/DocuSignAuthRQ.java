package com.inkd.auth.model.dto.docusign;

import java.io.Serializable;

public class DocuSignAuthRQ implements Serializable {

    private static final long serialVersionUID = 2661715429356151165L;

    private String code;

    private String grantType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
