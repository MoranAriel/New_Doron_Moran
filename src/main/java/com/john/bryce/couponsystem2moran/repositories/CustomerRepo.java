package com.john.bryce.couponsystem2moran.repositories;

import com.john.bryce.couponsystem2moran.entities.Company;
import com.john.bryce.couponsystem2moran.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, long id);

}
