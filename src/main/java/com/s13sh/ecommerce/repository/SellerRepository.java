package com.s13sh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.ecommerce.dto.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

	boolean existsByEmail(String email);
	boolean existsByMobile(long mobile);
	Seller findByEmail(String email);

}
