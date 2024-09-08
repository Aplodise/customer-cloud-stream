package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;
import com.roman.customer_cloud_stream.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
