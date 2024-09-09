package com.roman.customer_cloud_stream.controller;


import com.roman.customer_cloud_stream.controller.dto.CustomerDTO;
import com.roman.customer_cloud_stream.domain.*;
import com.roman.customer_cloud_stream.mapper.CustomerMapper;
import com.roman.customer_cloud_stream.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid final CustomerDTO customerDTO){
        var createdCustomer = customerService.createCustomer(CustomerMapper.mapToCustomer(customerDTO));
        return ResponseEntity.ok(createdCustomer);
    }
}
