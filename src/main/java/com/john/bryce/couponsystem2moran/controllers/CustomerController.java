package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.entities.Customer;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.services.AdminService;
import com.john.bryce.couponsystem2moran.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/coupon/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void PurchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable long couponId) throws CouponSystemException {
        customerService.PurchaseCoupon(token, couponId);
    }

    @GetMapping("/coupons")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return customerService.getCustomerCoupons(token);

    }

    @GetMapping("/coupons-by-max-price")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token, @RequestParam double maxPrice) throws CouponSystemException {
        return customerService.getCustomerCoupons(token, maxPrice);
    }

    @GetMapping("/coupons-by-category")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category) throws CouponSystemException {
        return customerService.getCustomerCoupons(token, category);
    }

    @GetMapping("/customer-details")
    @ResponseStatus(HttpStatus.OK) // 200
    public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return customerService.getCustomerDetails(token);
    }


}
