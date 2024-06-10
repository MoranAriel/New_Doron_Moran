package com.john.bryce.couponsystem2moran.tests;


import com.john.bryce.couponsystem2moran.security.ClientType;
import com.john.bryce.couponsystem2moran.security.LoginRequest;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest  implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/company/";
    private UUID token;

    @Override
    public void run(String... args) throws Exception {
        loginTest();
    }

    private void loginTest() {
        LoginRequest loginRequest = new LoginRequest("Alipress@gmail.com", "12345", ClientType.COMPANY);
        HttpEntity<LoginRequest> httpEntity = new HttpEntity<>(loginRequest);

        ResponseEntity<LoginResponse> responseEntity  = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST, httpEntity, LoginResponse.class);
        token = Objects.requireNonNull(responseEntity.getBody()).getToken();
        System.out.println("Login Company Test:");
        System.out.println(responseEntity.getStatusCode() + "  " + token);
        }



}
