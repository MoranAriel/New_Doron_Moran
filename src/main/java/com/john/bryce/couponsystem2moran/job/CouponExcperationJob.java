package com.john.bryce.couponsystem2moran.job;

import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
@RequiredArgsConstructor
public class CouponExcperationJob extends Thread{


        private final CouponRepo couponRepo;



        @Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000 * 60)
        public void deleteCouponsExpired(){
            couponRepo.deleteExpiredPurchasedCoupons();
            couponRepo.deleteExpiredCoupons();
        }


    }

