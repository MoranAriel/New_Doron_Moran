package com.john.bryce.couponsystem2moran;

import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.repositories.CompanyRepo;
import com.john.bryce.couponsystem2moran.repositories.CouponRepo;
import com.john.bryce.couponsystem2moran.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CouponSystem2MoranApplication {

	public static void main(String[] args) {

		SpringApplication.run(CouponSystem2MoranApplication.class, args);
		System.out.println("Coupon system is running");


	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
