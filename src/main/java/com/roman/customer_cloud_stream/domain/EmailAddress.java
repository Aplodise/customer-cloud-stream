package com.roman.customer_cloud_stream.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable
public record EmailAddress(String emailAddress) {
    public EmailAddress{
        Objects.requireNonNull(emailAddress, "The email cannot be null");
        Assert.isTrue(!emailAddress.isBlank(), "The email cannot be blank");
        Assert.isTrue(emailAddress.contains("@"), "Email needs to contain @");
    }

    public static EmailAddress of(String emailAddress) {

        return new EmailAddress(emailAddress);
    }

}
