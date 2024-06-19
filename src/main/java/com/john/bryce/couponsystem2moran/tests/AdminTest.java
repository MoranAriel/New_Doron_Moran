package com.john.bryce.couponsystem2moran.tests;

import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.ClientType;
import com.john.bryce.couponsystem2moran.security.LoginRequest;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/admin/";
    private UUID token;

    @Override
    public void run(String... args) throws Exception {
        loginTest();
        addCompanyTest();
        updateCompanyTest();
        deleteCompanyTest();
        getCompaniesTest();
        getCompanyTest();
    }

    public void loginTest(){
        LoginRequest loginRequest = new LoginRequest("admin@admin.com", "admin", ClientType.ADMIN);
        HttpEntity<LoginRequest> httpEntity = new HttpEntity<>(loginRequest);

        ResponseEntity<LoginResponse> responseEntity  = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST, httpEntity, LoginResponse.class);
        token = Objects.requireNonNull(responseEntity.getBody()).getToken();
        System.out.println("Login Admin Test:");
        System.out.println(responseEntity.getStatusCode() + "  " + token);
    }

    public void addCompanyTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Company company = new Company(0, "Glikson Red Rental", "glikson@gmail.com", "1234", null);
        HttpEntity<Company> httpEntity = new HttpEntity<>(company, httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "company", HttpMethod.POST, httpEntity, Void.class);
        System.out.println("Add Company Test:");
        System.out.println(responseEntity.getStatusCode());

        Company company2 = new Company(0, "MDK", "mdk@gmail.com", "1234", null);
        HttpEntity<Company> httpEntity2 = new HttpEntity<>(company2, httpHeaders);
        ResponseEntity<Void> responseEntity2  = restTemplate.exchange(url + "company", HttpMethod.POST, httpEntity2, Void.class);
        System.out.println("Add Company 2 Test:");
        System.out.println(responseEntity2.getStatusCode());
    }

    public void updateCompanyTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Company company = new Company(1, "Glikson Red Rental", "glikson@gmail.com", "12345", null);
        HttpEntity<Company> httpEntity = new HttpEntity<>(company, httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "company", HttpMethod.PUT, httpEntity, Void.class);
        System.out.println("Update Company Test:");
        System.out.println(responseEntity.getStatusCode());
    }

    public void deleteCompanyTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

//
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "company/2", HttpMethod.DELETE, httpEntity, Void.class);
        System.out.println("Delete Company Test:");
        System.out.println(responseEntity.getStatusCode());
    }


    public void getCompaniesTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

//
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<Company>> responseEntity  = restTemplate.exchange(url + "companies", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Company>>() {});
        System.out.println("Get Companies Test:");
        System.out.println(responseEntity.getBody());
    }

    public void getCompanyTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

 /// Body (Optional) + Token
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<Company> responseEntity  = restTemplate.exchange(url + "company/1", HttpMethod.GET, httpEntity, Company.class );
        System.out.println("Get Company Test:");
        System.out.println(responseEntity.getBody());
    }

}
