package com.s13sh.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.ecommerce.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findBySeller_id(int id);

	List<Product> findByApprovedTrue();

    List<Product> findByName(String name);

}
