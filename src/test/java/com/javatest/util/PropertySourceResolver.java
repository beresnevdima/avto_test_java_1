package com.javatest.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertySourceResolver {
    // iam
    private String iamSecurityBaseUrl = "https://iam.mono.st4g3.com/AuthServer/sessions/open?lang=RU";
    private String iamSessionPassword= "UumuiNg1uor2foo0Aushaizah";
    private String iamSessionLogin= "ftTesting";
    private String iamSessionAuthn= "EXCL";

    private String nirvanaApiBaseUrl = "https://nirvana-api.mono.t3zt.com";


}
