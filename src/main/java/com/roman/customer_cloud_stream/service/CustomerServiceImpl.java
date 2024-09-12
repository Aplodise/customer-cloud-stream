package com.roman.customer_cloud_stream.service;

import com.roman.customer_cloud_stream.domain.Customer;
import com.roman.customer_cloud_stream.domain.EmailAddress;
import com.roman.customer_cloud_stream.messaging.event.CustomerDTO;
import com.roman.customer_cloud_stream.messaging.event.CustomerEvent;
import com.roman.customer_cloud_stream.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;
import java.time.Instant;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final Sinks.Many<Message<?>> customerProducer;

    private static final String EVENT_HEADER = "X-EVENT-TYPE";
    @Transactional
    @Override
    public Customer createCustomer(final Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        var customerCreatedEvent = new CustomerEvent.CustomerCreated(createdCustomer.getId(),
                Instant.now(), CustomerMapper.mapToCustomerDTO(createdCustomer));

        Message<CustomerEvent.CustomerCreated> customerCreatedMessage = MessageBuilder.withPayload(customerCreatedEvent)
                        .setHeader(EVENT_HEADER, "CustomerCreated").build();
        customerProducer.tryEmitNext(customerCreatedMessage);
        return createdCustomer;
    }

    @Transactional
    @Override
    public void changeEmail(final Long customerId, final EmailAddress emailAddress) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException(String.format("Couldn't find a customer by id: %s", customerId)));
        customer.changeEmail(emailAddress);
        CustomerEvent.EmailChanged emailChanged = new CustomerEvent.EmailChanged(customer.getId(), Instant.now(), CustomerMapper.mapToCustomerDTO(customer));
        Message<CustomerEvent.EmailChanged> emailChangedMessage = MessageBuilder.withPayload(emailChanged)
                .setHeader(EVENT_HEADER, "EmailChanged")
                        .build();
        customerProducer.tryEmitNext(emailChangedMessage);
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
