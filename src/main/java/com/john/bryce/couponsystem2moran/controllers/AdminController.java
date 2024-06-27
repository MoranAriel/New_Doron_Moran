package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Customer;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import com.john.bryce.couponsystem2moran.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // http://localhost:8080/api/admin/company
    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(token, company);
    }

    @PutMapping("/company")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void updateCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(token, company);
    }

    // http://localhost:8080/api/admin/company/5
    @DeleteMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable long companyId) throws CouponSystemException {
        adminService.deleteCompany(token, companyId);
    }


    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK) // 200
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return adminService.getAllCompanies(token);
    }

    @GetMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getOneCompany(@RequestHeader("Authorization") UUID token, @PathVariable long companyId) throws CouponSystemException {
        return adminService.getOneCompany(token, companyId);
    }


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void addCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(token, customer);
    }

    @PutMapping("/customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemException {
       adminService.updateCustomer(token, customer);
    }

    @DeleteMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization") UUID token,@PathVariable long customerId) throws CouponSystemException {
        adminService.deleteCustomer(token, customerId);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return adminService.getAllCustomers(token);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getOneCustomer(@RequestHeader("Authorization") UUID token, @PathVariable long customerId) throws CouponSystemException {
        return adminService.getOneCustomer(token, customerId);
    }







}
