package com.john.bryce.couponsystem2moran.services;

import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.security.ClientType;
import com.john.bryce.couponsystem2moran.security.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginManager {

    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public LoginResponse manageLogin(String email, String password, ClientType clientType) throws CouponSystemException {
        LoginResponse loginResponse = null;
        switch (clientType) {
            case ADMIN: {
                loginResponse = adminService.login(email, password);
                break;
            }
            case COMPANY: {
                loginResponse = companyService.login(email, password);
                break;

            }
            case CUSTOMER:{
                loginResponse = customerService.login(email, password);
                break;
            }
            default: {
                throw new CouponSystemException("Invlaid Client Type");
            }
        }
        return loginResponse;
    }

}
