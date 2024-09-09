package com.roman.customer_cloud_stream.messaging;

import com.roman.customer_cloud_stream.domain.*;
import com.roman.customer_cloud_stream.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CustomerMessaging {
    private final CustomerService customerService;

    @Bean
    public Supplier<Customer> customerSupplier(){
        return () -> {
            Customer customer = Customer.create(
                    FirstName.of("James"),
                    LastName.of("Gosling"),
                    BirthDate.of(LocalDate.of(1965, 11, 21)),
                    EmailAddress.of("james.jdk@hotmail.com"));
            return customerService.createCustomer(customer);
        };
    }
}
