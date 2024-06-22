package com.john.bryce.couponsystem2moran.tests;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.entities.Customer;
import com.john.bryce.couponsystem2moran.repositories.CompanyRepo;
import com.john.bryce.couponsystem2moran.repositories.CouponRepo;
import com.john.bryce.couponsystem2moran.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(1)
public class InitData implements CommandLineRunner {

    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;



    @Override
    public void run(String... args) throws Exception {

        initCompanies();
        initCoupons();
        initCustomers();
        initPurchases();

    }

    public void initCompanies () {
        Company company1 = new Company(0, "Company1", "company1@gmail.com", "1234", null);
        Company company2 = new Company(0, "Company2", "company2@gmail.com", "1234", null);
        companyRepo.saveAll(List.of(company1, company2));
    }

    public void initCoupons () {
        Company company1 = new Company(1, "Company1", "company1@gmail.com", "1234", null);
        Company company2 = new Company(2, "Company2", "company2@gmail.com", "1234", null);

        Coupon coupon1 = new Coupon(0,company1, Category.RENTAL_GEAR,"Coupon1","Coupon1 desc", LocalDate.of(2024,6,1),LocalDate.of(2024,6,30),10,100,null);
        Coupon coupon2 = new Coupon(0,company1, Category.NEW_GEAR,"Coupon2","Coupon2 desc", LocalDate.of(2023,6,1),LocalDate.of(2023,7,31),10,100,null);
        Coupon coupon3 = new Coupon(0,company2, Category.PRODUCTION,"Coupon3","Coupon3 desc", LocalDate.of(2024,6,1),LocalDate.of(2024,6,30),10,100,null);
        Coupon coupon4 = new Coupon(0,company2, Category.POST_PRODUCTION,"Coupon4","Coupon4 desc", LocalDate.of(2024,6,1),LocalDate.of(2024,7,31),10,100,null);
        couponRepo.saveAll(List.of(coupon1, coupon2, coupon3, coupon4));
    }

    public void initCustomers () {
        Customer customer1 = new Customer(0, "Customer1", "Customer1", "Customer1@gmail.com", "1234", null);
        Customer customer2 = new Customer(0, "Customer2", "Customer2", "Customer2@gmail.com", "1234", null);
        customerRepo.saveAll(List.of(customer1, customer2));

    }

    public void initPurchases () {
        couponRepo.purchaseCoupon(1, 1);
        couponRepo.purchaseCoupon(1, 2);
        couponRepo.purchaseCoupon(2, 2);
        couponRepo.purchaseCoupon(2, 3);
        couponRepo.purchaseCoupon(2, 4);
    }


}
