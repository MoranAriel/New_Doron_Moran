package com.john.bryce.couponsystem2moran.tests;


import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.repositories.CompanyRepo;
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
public class CompanyTest  implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/company/";
    private UUID token;

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println("Company - Tests");
        loginTest();
        addCouponTest();
        updateCouponTest();
        deleteCouponTest();
        getCompanyCouponsTest();
        getCompanyCouponsByCategoryTest();
        getCompanyCouponsByPriceTest();
        getCompanyDetails();
    }

    private void loginTest() {
        LoginRequest loginRequest = new LoginRequest("Glikson@gmail.com", "12345", ClientType.COMPANY);
        HttpEntity<LoginRequest> httpEntity = new HttpEntity<>(loginRequest);

        ResponseEntity<LoginResponse> responseEntity  = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST, httpEntity, LoginResponse.class);
        token = Objects.requireNonNull(responseEntity.getBody()).getToken();
        System.out.println("Login Company Test:");
        System.out.println(responseEntity.getStatusCode() + "  " + token);
        }

    public void addCouponTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


        Coupon coupon = new Coupon(0,null, Category.RENTAL_GEAR,"20% off","extra 20% off weekend rentals", LocalDate.of(2024,6,1),LocalDate.of(2024,6,30),10,100,null);
        HttpEntity<Coupon> httpEntity = new HttpEntity<>(coupon, httpHeaders);
        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "coupon", HttpMethod.POST, httpEntity, Void.class);
        System.out.println("Add Coupon Test:");
        System.out.println(responseEntity.getStatusCode());

        Coupon coupon2 = new Coupon(0,null, Category.NEW_GEAR,"Mirrorless Cameras Sale","$100 off Sony Alpha A7iii + 24mm lens bundle ", LocalDate.of(2024,6,1),LocalDate.of(2024,8,31),10,7000,null);
        HttpEntity<Coupon> httpEntity2 = new HttpEntity<>(coupon2, httpHeaders);

        ResponseEntity<Void> responseEntity2  = restTemplate.exchange(url + "coupon", HttpMethod.POST, httpEntity2, Void.class);
        System.out.println("Add Coupon2 Test:");
        System.out.println(responseEntity2.getStatusCode());

    }
    public void updateCouponTest() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());


     Coupon coupon = new Coupon(5, null, Category.RENTAL_GEAR,"60% off","extra 60% off weekend rentals", LocalDate.of(2024,6,1),LocalDate.of(2024,7,31),10,100,null);
        HttpEntity<Coupon> httpEntity = new HttpEntity<>(coupon, httpHeaders);
        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "coupon", HttpMethod.PUT, httpEntity, Void.class);
        System.out.println("Update Coupon Test:");
        System.out.println(responseEntity.getStatusCode());
    }
//
    public void deleteCouponTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

//
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "coupon/6", HttpMethod.DELETE, httpEntity, Void.class);
        System.out.println("Delete Coupon Test:");
        System.out.println(responseEntity.getStatusCode());
    }


    public void getCompanyCouponsTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {});
        System.out.println("Get Coupons Test:");
        System.out.println(responseEntity.getBody());
    }

    public void getCompanyCouponsByCategoryTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        /// Body (Optional) + Token
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons-category?category=RENTAL_GEAR", HttpMethod.GET, httpEntity,  new ParameterizedTypeReference<List<Coupon>>() {} );
        System.out.println("Get Coupon Test By Category:");
        System.out.println(responseEntity.getBody());
    }
    public void getCompanyCouponsByPriceTest() throws CouponSystemException {
//        To get token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        /// Body (Optional) + Token
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons-by-max-price?maxPrice=8000", HttpMethod.GET, httpEntity,  new ParameterizedTypeReference<List<Coupon>>() {} );
        System.out.println("Get Coupon Test By Price:");
        System.out.println(responseEntity.getBody());
    }

    public void getCompanyDetails() throws CouponSystemException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token.toString());

        /// Body (Optional) + Token
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Company> responseEntity  = restTemplate.exchange(url + "company-details", HttpMethod.GET, httpEntity, Company.class );
        System.out.println("Get company details test:");
        System.out.println(responseEntity.getBody());
    }


    }
