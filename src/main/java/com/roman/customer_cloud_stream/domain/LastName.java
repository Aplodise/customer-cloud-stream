package com.roman.customer_cloud_stream.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable
public record LastName(String lastName) {

    public LastName {
        Objects.requireNonNull(lastName, "The last name cannot be null");
        Assert.isTrue(!lastName.isBlank(), "Last name cannot be empty");
    }

    public static LastName of(final String value) {
        return new LastName(value);
    }
}