package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;
import com.roman.customer_cloud_stream.domain.EmailAddress;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    void changeEmail(Long customerId, EmailAddress emailAddress);
}
