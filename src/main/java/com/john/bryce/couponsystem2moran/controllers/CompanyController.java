package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Company;
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
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService companyService;

//    Post/ Delete/ Put/ Get

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        return companyService.addCoupon(token, coupon);
    }

    @PutMapping("/coupon")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Coupon updateCoupon(@RequestHeader("Authorization") UUID token,  @RequestBody Coupon coupon) throws CouponSystemException {
       return companyService.updateCoupon(token, coupon);
    }

    @DeleteMapping("/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") UUID token,@PathVariable long couponId) throws CouponSystemException {
        companyService.deleteCoupon(token, couponId);
    }
    @GetMapping("/coupons")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return companyService.getCompanyCoupons(token);
    }


    @GetMapping("/coupons-category")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category) throws CouponSystemException {
        return companyService.getCompanyCoupons(token,category);
    }


    @GetMapping("/coupons-by-max-price")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam double maxPrice) throws CouponSystemException {
        return companyService.getCompanyCoupons(token,maxPrice);
    }
    @GetMapping("/company-details")
    @ResponseStatus(HttpStatus.OK) // 200
    public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return companyService.getCompanyDetails(token);
    }



}
