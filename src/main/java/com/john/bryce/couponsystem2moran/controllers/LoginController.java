package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.LoginRequest;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import com.john.bryce.couponsystem2moran.services.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {
    private final LoginManager loginManager;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CouponSystemException {
        return loginManager.manageLogin(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
    }
}
