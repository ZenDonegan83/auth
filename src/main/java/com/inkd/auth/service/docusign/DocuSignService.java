package com.inkd.auth.service.docusign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.docusign.DocuSignAuthRS;
import com.inkd.auth.model.dto.docusign.DocuSignEnvelopeRQ;
import com.inkd.auth.model.dto.docusign.DocuSignUserInfoRS;
import com.inkd.auth.service.docusign.support.DocuSignAuthorizeCodeGrantAccessTokenAPI;
import com.inkd.auth.service.docusign.support.DocuSignRefreshAccessTokenAPI;
import com.inkd.auth.service.docusign.support.DocuSignUserInfoAPI;
import com.inkd.auth.service.docusign.support.DocuSignCreateEnvelopeAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocuSignService {

    private static String accessToken
            = "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAgABwAA3T7oreLaSAgAAF2j4ED62kgCANkjenJfpnRAg7w8ya9uJkcVAAEAAAAYAAEAAAAFAAAADQAkAAAAOGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2IgAkAAAAOGI2NDk2ZWUtYTdlMC00MjBlLTg3NzAtNDMyZjQzMDZmYTY2MAAA7SaXneLaSDcAFQaSdZPIwkGS12ZtBvHcPQ.VCFIcr85f59iZwN9-r7AVHPoo4OcxnBJq4Ta8reR7P-UaIbfCK92v6IHrx24rxSIB40R84lsTJAva_QLDHHSCnavVVBctYbSSmcosO4uSP5DsaatUd8oKHETDiujapCPGhFZMqNubRoajeTRbfzuE3c9oawNTFlogd-U_T_Oumz0Id6Raxl19yDxcEd7WEnsZKP9ckc79cqeWO1OB2lBUJw7GWOr0wqZByH2gaNi50M28t5d8ztFrxoPex2aqTknRHU_GcE9H7UBfcqW2DVWBO49-tdfr4t2lhne-h37KcjIp_Yiby2YbHHiCsjQJoiuaVX_ZZGDuW3ONuSixfYkww";

    @Autowired
    private DocuSignUserInfoAPI dociSignUserInfoAPI;

    @Autowired
    private DocuSignCreateEnvelopeAPI docuSignCreateEnvelopeAPI;

    @Autowired
    private DocuSignRefreshAccessTokenAPI docuSignRefreshAccessTokenAPI;

    @Autowired
    private DocuSignAuthorizeCodeGrantAccessTokenAPI docuSignAuthorizeCodeGrantAccessTokenAPI;

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

    public void sendSignEnvelope(DocuSignEnvelopeRQ envelopeRQ) {
        this.docuSignCreateEnvelopeAPI.sendSignEnvelope(envelopeRQ);
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
}

