package com.javatest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NirvanaClient {
    private Long clientId;
    private String clientSt;
    private String clientLName;
    private String clientFName;
    private String clientMName;
    private String clientOkpo;
    private String clientSex;
    private String clientDb;
    private String clientCountryRes;
    private String clientNameEn;
    private String comName;
    private String clientType;
    private String clientDTS;
    private String clientDTM;
    private String creatorLogin;
    private String editorLogin;
    private String clientRemark;
    private Error error;

    public NirvanaClient() {
    }

    public NirvanaClient(Error error) {
        this.error = error;
    }

}
