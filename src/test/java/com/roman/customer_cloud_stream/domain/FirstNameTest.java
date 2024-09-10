package com.roman.customer_cloud_stream.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstNameTest {

    @Test
    void shouldCreateFirstName(){
        String name = "John";

        FirstName firstName = FirstName.of(name);

        assertNotNull(firstName);
        assertEquals(name, firstName.firstName());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenFirstNameIsNull(){

        String name = null;

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> FirstName.of(name));

        assertEquals("The first name cannot be null", nullPointerException.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenFirstNameIsBlank(){
        String name = "";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> FirstName.of(name));
        assertEquals("First name cannot be empty", illegalArgumentException.getMessage());
    }
}