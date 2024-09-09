package com.roman.customer_cloud_stream.messaging;

import com.roman.customer_cloud_stream.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.util.function.Supplier;

@Configuration
public class CustomerMessaging {

    @Bean
    public Sinks.Many<Customer> customerProducer(){
        return Sinks.many()
                .replay()
                .latest();
    }

    @Bean
    public Supplier<Flux<Customer>> customerSupplier(){
        return () -> customerProducer().asFlux();
    }
}
