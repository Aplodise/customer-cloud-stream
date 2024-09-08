package com.roman.customer_cloud_stream.repository;

import com.roman.customer_cloud_stream.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
