package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;
import com.roman.customer_cloud_stream.domain.EmailAddress;
import com.roman.customer_cloud_stream.messaging.event.CustomerDTO;
import com.roman.customer_cloud_stream.messaging.event.CustomerEvent;
import com.roman.customer_cloud_stream.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;
import java.time.Instant;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final Sinks.Many<CustomerEvent> customerProducer;

    @Transactional
    @Override
    public Customer createCustomer(final Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        var customerCreatedEvent = new CustomerEvent.CustomerCreated(createdCustomer.getId(),
                Instant.now(), CustomerMapper.mapToCustomerDTO(createdCustomer));
        customerProducer.tryEmitNext(customerCreatedEvent);
        return createdCustomer;
    }

    @Transactional
    @Override
    public void changeEmail(final Long customerId, final EmailAddress emailAddress) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException(String.format("Couldn't find a customer by id: %s", customerId)));
        customer.changeEmail(emailAddress);
        customerProducer.tryEmitNext(new CustomerEvent.EmailChanged(customer.getId(), Instant.now(), CustomerMapper.mapToCustomerDTO(customer)));
    }

    interface CustomerMapper {
        static CustomerDTO mapToCustomerDTO(final Customer customerCreated) {
            return new CustomerDTO(customerCreated.getFirstName().firstName(),
                    customerCreated.getLastName().lastName(),
                    customerCreated.getBirthDate().birthDate(),
                    customerCreated.getEmailAddress().emailAddress(),
                    customerCreated.getSsn().ssn());
        }
    }
}
