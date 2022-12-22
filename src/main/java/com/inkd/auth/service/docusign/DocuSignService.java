package com.inkd.auth.service.docusign;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.docusign.DocuSignUserInfoRS;
import com.inkd.auth.model.dto.pdf.PdfFileSignInRQ;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DocuSignService {

    DocuSignUserInfoRS getUserInfo() throws AppsException;

    void signInPDF(PdfFileSignInRQ pdfFileSignInRQ) throws AppsException, IOException;
}
