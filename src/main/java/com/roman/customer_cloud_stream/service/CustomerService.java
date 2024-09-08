package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
}
