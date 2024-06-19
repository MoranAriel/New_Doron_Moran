package com.john.bryce.couponsystem2moran.tests;


import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.entities.Customer;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.ClientType;
import com.john.bryce.couponsystem2moran.security.LoginRequest;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Order(3)
@RequiredArgsConstructor
public class CustomerTest implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/customer/";
    private UUID token;


    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println("Customer - Tests");
        loginTest();
        PurchaseCouponTest();
        getCustomerCoupons();

    }


    private void loginTest() {
        LoginRequest loginRequest = new LoginRequest("moran@gmail.com", "12345", ClientType.CUSTOMER);
        HttpEntity<LoginRequest> httpEntity = new HttpEntity<>(loginRequest);

        ResponseEntity<LoginResponse> responseEntity  = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST, httpEntity, LoginResponse.class);
        token = Objects.requireNonNull(responseEntity.getBody()).getToken();
        System.out.println("Login Customer Test:");
        System.out.println(responseEntity.getStatusCode() + "  " + token);
    }

    public void PurchaseCouponTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        HttpEntity<Coupon> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "coupon/1", HttpMethod.POST, httpEntity, Void.class);
        System.out.println("Purchase Coupon Test:");
        System.out.println(responseEntity.getStatusCode());
    }

    public void getCustomerCoupons() throws CouponSystemException {
   //     To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Customer>> responseEntity  = restTemplate.exchange(url + "coupons", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Customer>>() {});
        System.out.println("Get Customers' coupons Test:");
        System.out.println(responseEntity.getBody());
    }
}






