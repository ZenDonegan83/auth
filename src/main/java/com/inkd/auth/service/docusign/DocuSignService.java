package com.inkd.auth.service.docusign;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.docusign.DocuSignUserInfoRS;
import org.springframework.stereotype.Service;

@Service
public interface DocuSignService {

    DocuSignUserInfoRS getUserInfo() throws AppsException;

}
