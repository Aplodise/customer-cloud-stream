package com.roman.customer_cloud_stream.contracts;

import com.roman.customer_cloud_stream.domain.*;
import com.roman.customer_cloud_stream.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMessageVerifier
public class ShouldPublishCustomerCreatedBase {

    @Autowired
    private CustomerService customerService;

    void shouldPublishCustomerCreated(){
        var customer = Customer.create(
                FirstName.of("William"),
                LastName.of("Butcher"),
                BirthDate.of(LocalDate.of(2000, 12, 12)),
                EmailAddress.of("email@gmail.com"),
                SSN.of(343434342)
        );
        Customer customerCreated = customerService.createCustomer(customer);

    }
}
