package com.roman.customer_cloud_stream.mapper;

import com.roman.customer_cloud_stream.controller.dto.CustomerDTO;
import com.roman.customer_cloud_stream.domain.*;

public class CustomerMapper {

    public static Customer mapToCustomer(final CustomerDTO customerDTO){
        Customer customer = Customer.create(
                FirstName.of(customerDTO.firstName()),
                LastName.of(customerDTO.lastName()),
                BirthDate.of(customerDTO.birthDate()),
                EmailAddress.of(customerDTO.emailAddress()),
                SSN.of(customerDTO.ssn())
        );
        return customer;
    }
}
