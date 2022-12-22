package com.inkd.auth.controller.docusign;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.pdf.PdfFileSignInRQ;
import com.inkd.auth.service.docusign.DocuSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/docusign")
public class DocusignTestController {

    @Autowired
    private DocuSignService docuSignService;

    @GetMapping(value = "/signInPDF", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<String>> signInPDF(@RequestBody PdfFileSignInRQ signInRQ) {
        ResponseDTO<String> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.docuSignService.signInPDF(signInRQ);

            response.setResult("SUCCESS");
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (IOException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
