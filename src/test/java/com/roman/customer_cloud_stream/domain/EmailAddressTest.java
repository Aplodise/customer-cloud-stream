package com.roman.customer_cloud_stream.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class EmailAddressTest {

    @Test
    void givenValidEmailWhenCreateThenEmailReturned(){
        var emailAddress = "frofreof@gmail.com";

        var actualEmailAddress = EmailAddress.of(emailAddress);
        Assertions.assertNotNull(actualEmailAddress);
        Assertions.assertEquals(emailAddress, actualEmailAddress.emailAddress());
    }
    @Test
    void givenInvalidEmailWhenCreateThenNullPointerException(){
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> EmailAddress.of(null));
        Assertions.assertEquals("The email cannot be null", exception.getMessage());

    }
}