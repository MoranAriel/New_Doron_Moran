//package com.john.bryce.couponsystem2moran.tests;
//
//
//import com.john.bryce.couponsystem2moran.entities.Category;
//import com.john.bryce.couponsystem2moran.entities.Company;
//import com.john.bryce.couponsystem2moran.entities.Coupon;
//import com.john.bryce.couponsystem2moran.entities.Customer;
//import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
//import com.john.bryce.couponsystem2moran.security.ClientType;
//import com.john.bryce.couponsystem2moran.security.LoginRequest;
//import com.john.bryce.couponsystem2moran.security.LoginResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@Component
//@Order(4)
//@RequiredArgsConstructor
//public class CustomerTest implements CommandLineRunner {
//
//    private final RestTemplate restTemplate;
//    private String url = "http://localhost:8080/api/customer/";
//    private UUID token;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println();
//        System.out.println("Customer - Tests");
//        loginTest();
//        PurchaseCouponTest();
//        getCustomerCoupons();
//        getCustomerCouponsByCategoryTest();
//        getCustomerCouponsByPrice();
//        getCompanyDetails();
//
//
//    }
//
//
//    private void loginTest() {
//        LoginRequest loginRequest = new LoginRequest("moran@gmail.com", "12345", ClientType.CUSTOMER);
//        HttpEntity<LoginRequest> httpEntity = new HttpEntity<>(loginRequest);
//
//        ResponseEntity<LoginResponse> responseEntity  = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST, httpEntity, LoginResponse.class);
//        token = Objects.requireNonNull(responseEntity.getBody()).getToken();
//        System.out.println("Login Customer Test:");
//        System.out.println(responseEntity.getStatusCode() + "  " + token);
//    }
//
//    public void PurchaseCouponTest() throws CouponSystemException {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", token.toString());
//
//        HttpEntity<Coupon> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<Void> responseEntity  = restTemplate.exchange(url + "coupon/5", HttpMethod.POST, httpEntity, Void.class);
//        System.out.println("Purchase Coupon Test:");
//        System.out.println(responseEntity.getStatusCode());
//    }
//
//    public void getCustomerCoupons() throws CouponSystemException {
//   //     To get token
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", token.toString());
//
//        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {});
//        System.out.println("Get Customer coupons Test:");
//        System.out.println(responseEntity.getBody());
//    }
//
//    public void getCustomerCouponsByCategoryTest() throws CouponSystemException {
//        //     To get token
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", token.toString());
//
//        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons-by-category?category=RENTAL_GEAR", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {});
//        System.out.println("Get Customer coupons by category Test:");
//        System.out.println(responseEntity.getBody());
//    }
//
//    public void getCustomerCouponsByPrice() throws CouponSystemException {
//        //     To get token
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", token.toString());
//
//        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<Coupon>> responseEntity  = restTemplate.exchange(url + "coupons-by-max-price?maxPrice=8000", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {});
//        System.out.println("Get Customer coupons by Max Price Test:");
//        System.out.println(responseEntity.getBody());
//    }
//
//    public void getCompanyDetails() throws CouponSystemException {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", token.toString());
//
//        /// Body (Optional) + Token
//        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<Customer> responseEntity  = restTemplate.exchange(url + "customer-details", HttpMethod.GET, httpEntity, Customer.class );
//        System.out.println("Get customer details test:");
//        System.out.println(responseEntity.getBody());
//    }
//
//}
//
//
//
//
//
//
