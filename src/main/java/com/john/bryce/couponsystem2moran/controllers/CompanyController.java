package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.services.AdminService;
import com.john.bryce.couponsystem2moran.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

//    Post/ Delte/Put Get

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        companyService.addCoupon(token, coupon);
    }

    @PutMapping("/coupon")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@RequestHeader("Authorization") UUID token,  @RequestBody Coupon coupon) throws CouponSystemException {
        companyService.updateCoupon(token, coupon);
    }

    @GetMapping("/coupons-category")
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category) throws CouponSystemException {
        return getCompanyCoupons(token,category);
    }




    }
