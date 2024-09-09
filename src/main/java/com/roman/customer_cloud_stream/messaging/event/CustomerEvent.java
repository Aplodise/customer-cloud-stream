package com.roman.customer_cloud_stream.messaging.event;



import java.io.Serializable;
import java.time.Instant;

public sealed interface CustomerEvent extends Serializable {

    record CustomerCreated(Long customerId, Instant createdAt, CustomerDTO customer) implements CustomerEvent{

    }
    record EmailChanged(Long customerId, Instant changedAt, CustomerDTO customer) implements CustomerEvent{

    }
}
