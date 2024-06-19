package com.john.bryce.couponsystem2moran.services;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.entities.Customer;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.ClientType;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import com.john.bryce.couponsystem2moran.security.TokenInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService extends ClientService{
    @Override
    public LoginResponse login(String email, String password) throws CouponSystemException {
        Customer customerFromDb = customerRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSystemException("Email or password is wrong"));
        TokenInformation tokenInformation = new TokenInformation(customerFromDb.getId(), email, LocalDateTime.now().plusDays(1), ClientType.CUSTOMER);
        UUID token = tokenManager.addToken(tokenInformation);
        return new LoginResponse(token, customerFromDb.getId(),  email, customerFromDb.getFirstName(), ClientType.CUSTOMER);
    }

    @Transactional
    public void PurchaseCoupon(UUID token, long couponId) throws CouponSystemException {
        long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        Coupon couponFromDb = couponRepo.findById(couponId).orElseThrow(() -> new CouponSystemException("Coupon not found"));

        if(couponFromDb.getAmount() <= 0) {
            throw new CouponSystemException("coupon out of stock");
        }

        if(couponFromDb.getEndDate().isBefore(LocalDate.now())) {
            throw new CouponSystemException("coupon is expired");
        }

        if(couponRepo.isCouponPurchasedBefore(customerId, couponId) == 1) {
            throw new CouponSystemException("coupon is already purchase before");
        }

        couponFromDb.setAmount(couponFromDb.getAmount() - 1);
        couponRepo.save(couponFromDb);
        couponRepo.purchaseCoupon(customerId, couponId);
    }





    public List<Coupon> getCustomerCoupons(UUID token) throws CouponSystemException {
        long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);

        if (!customerRepo.existsById(customerId)) {
            throw new CouponSystemException("Customer does not exist");
        }
        return couponRepo.findByCustomer(customerId);
    }
    public List<Coupon> getCustomerCoupons(UUID token, Category category) throws CouponSystemException {
        long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);

        if (!customerRepo.existsById(customerId)) {
            throw new CouponSystemException("Customer does not exist");
        }
        return couponRepo.findByCustomerIdAndCategory(customerId, category.name());

    }

    public List<Coupon> getCustomerCoupons(UUID token, double maxPrice) throws CouponSystemException {
        long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);

        if(maxPrice < 0) {
            throw new CouponSystemException("Max Price can not be below zero");

        }
        if (!customerRepo.existsById(customerId)) {
            throw new CouponSystemException("Customer does not exist");
        }
        return couponRepo.getCompanyCouponsByMaxPrice(customerId, maxPrice);
    }

    public Customer getCustomerDetails(UUID token) throws CouponSystemException {
        long customerId = tokenManager.validateToken(token, ClientType.CUSTOMER);
        return customerRepo.findById(customerId).orElseThrow(() -> new CouponSystemException("Customer does not exist"));
    }

}

