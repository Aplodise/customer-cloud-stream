package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;
import com.roman.customer_cloud_stream.domain.EmailAddress;
import com.roman.customer_cloud_stream.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final Sinks.Many<Customer> customerProducer;

    @Override
    public Customer createCustomer(final Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        customerProducer.tryEmitNext(createdCustomer);
        return createdCustomer;
    }

    @Override
    public void changeEmail(final Long customerId, final EmailAddress emailAddress) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException(String.format("Couldn't find a customer by id: %s", customerId)));
        customer.changeEmail(emailAddress);
    }
}
