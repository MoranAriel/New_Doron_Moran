package com.john.bryce.couponsystem2moran.services;

import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.repositories.CompanyRepo;
import com.john.bryce.couponsystem2moran.repositories.CouponRepo;
import com.john.bryce.couponsystem2moran.repositories.CustomerRepo;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import com.john.bryce.couponsystem2moran.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected  CompanyRepo companyRepo;
    @Autowired
    protected  CouponRepo couponRepo;
    @Autowired
    protected  CustomerRepo customerRepo;
    @Autowired
    protected TokenManager tokenManager;

    public abstract LoginResponse login(String email, String password) throws CouponSystemException;

}
