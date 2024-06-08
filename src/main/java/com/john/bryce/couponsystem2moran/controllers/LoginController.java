package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.LoginRequest;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import com.john.bryce.couponsystem2moran.services.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginManager loginManager;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CouponSystemException {
        return loginManager.manageLogin(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
    }
}
