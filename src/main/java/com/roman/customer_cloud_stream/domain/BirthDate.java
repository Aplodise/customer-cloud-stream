package com.roman.customer_cloud_stream.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public record BirthDate(LocalDate birthDate) {
    public BirthDate{
        Objects.requireNonNull(birthDate, "The birthdate cannot be null");
        Assert.isTrue(!birthDate.isAfter(LocalDate.now()), "The birthdate should be in past");
    }

    public static BirthDate of(LocalDate date){
        return new BirthDate(date);
    }
}
