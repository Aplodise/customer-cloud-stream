package com.roman.customer_cloud_stream.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable

public record FirstName(String firstName) {

    public FirstName {
        Objects.requireNonNull(firstName, "The first name cannot be null");
        Assert.isTrue(!firstName.isBlank(), "First name cannot be empty");
    }

    public static FirstName of(final String value) {
        return new FirstName(value);
    }
}