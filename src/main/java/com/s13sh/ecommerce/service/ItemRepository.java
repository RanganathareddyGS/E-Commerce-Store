package com.s13sh.ecommerce.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.ecommerce.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
