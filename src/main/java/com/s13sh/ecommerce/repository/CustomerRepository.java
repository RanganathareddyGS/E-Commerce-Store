package com.s13sh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.ecommerce.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	boolean existsByEmail(String email);
	boolean existsByMobile(long mobile);
	Customer findByEmail(String email);
}
