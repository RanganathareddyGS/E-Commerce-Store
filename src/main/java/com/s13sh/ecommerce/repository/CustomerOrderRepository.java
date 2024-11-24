package com.s13sh.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.ecommerce.dto.Customer;
import com.s13sh.ecommerce.dto.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

	List<CustomerOrder> findByCustomerAndPaymentIdIsNotNull(Customer customer);

}
