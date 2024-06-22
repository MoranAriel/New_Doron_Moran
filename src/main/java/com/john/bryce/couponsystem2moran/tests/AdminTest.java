package com.john.bryce.couponsystem2moran.tests;

import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Customer;
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
@Order(2)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/admin/";
    private UUID token;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ADMIN TEST");
        System.out.println("---company---");
        loginTest();
        addCompanyTest();
        updateCompanyTest();
        deleteCompanyTest();
        getCompaniesTest();
        getCompanyTest();
        System.out.println("---customers---");
        addCustomerTest();
        updateCustomerTest();
        deleteCustomerTest();
        getCustomersTest();
        getCustomerTest();
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


        Company company = new Company(3, "Glikson Red Rental", "glikson@gmail.com", "1234", null);
        HttpEntity<Company> httpEntity = new HttpEntity<>(company, httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "company", HttpMethod.POST, httpEntity, Void.class);
        System.out.println("Add Company Test:");
        System.out.println(responseEntity.getStatusCode());

        Company company2 = new Company(4, "MDK", "mdk@gmail.com", "1234", null);
        HttpEntity<Company> httpEntity2 = new HttpEntity<>(company2, httpHeaders);
        ResponseEntity<Void> responseEntity2  = restTemplate.exchange(url + "company", HttpMethod.POST, httpEntity2, Void.class);
        System.out.println("Add Company 2 Test:");
        System.out.println(responseEntity2.getStatusCode());
    }

    public void updateCompanyTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Company company = new Company(3, "Glikson Red Rental", "glikson@gmail.com", "12345", null);
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


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "company/4", HttpMethod.DELETE, httpEntity, Void.class);
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


        ResponseEntity<Company> responseEntity  = restTemplate.exchange(url + "company/3", HttpMethod.GET, httpEntity, Company.class );
        System.out.println("Get Company Test:");
        System.out.println(responseEntity.getBody());
    }

    public void addCustomerTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Customer customer = new Customer(0, "Moran", "Ariel", "moran@gmail.com", "1234", null);
        HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "customer", HttpMethod.POST, httpEntity, Void.class);
        System.out.println("Add Customer Test:");
        System.out.println(responseEntity.getStatusCode());

        Customer customer2 = new Customer(0, "Doron", "Berger", "doron@gmail.com", "1234", null);
        HttpEntity<Customer> httpEntity2 = new HttpEntity<>(customer2, httpHeaders);
        ResponseEntity<Void> responseEntity2  = restTemplate.exchange(url + "customer", HttpMethod.POST, httpEntity2, Void.class);
        System.out.println("Add Customer 2 Test:");
        System.out.println(responseEntity2.getStatusCode());
    }

    public void updateCustomerTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Customer customer = new Customer(3, "Moran", "Ariel Kuzi", "moran@gmail.com", "12345", null);
        HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "customer", HttpMethod.PUT, httpEntity, Void.class);
        System.out.println("Update Company Test:");
        System.out.println(responseEntity.getStatusCode());
    }
    public void deleteCustomerTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

//
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "customer/4", HttpMethod.DELETE, httpEntity, Void.class);
        System.out.println("Delete Customer Test:");
        System.out.println(responseEntity.getStatusCode());
    }

    public void getCustomersTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

//
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<Customer>> responseEntity  = restTemplate.exchange(url + "customers", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Customer>>() {});
        System.out.println("Get Customers Test:");
        System.out.println(responseEntity.getBody());
    }

    public void getCustomerTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        /// Body (Optional) + Token
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<Customer> responseEntity  = restTemplate.exchange(url + "customer/3", HttpMethod.GET, httpEntity, Customer.class );
        System.out.println("Get Customer Test:");
        System.out.println(responseEntity.getBody());
    }

}
