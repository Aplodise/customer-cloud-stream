package com.roman.customer_cloud_stream.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastNameTest {
    @Test
    void shouldCreateLastName(){
        String name = "Smith";

        LastName lastName = LastName.of(name);

        assertNotNull(lastName);
        assertEquals(name, lastName.lastName());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenLastNameIsNull(){

        String name = null;

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> LastName.of(name));

        assertEquals("The last name cannot be null", nullPointerException.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenLastNameIsBlank(){
        String name = "";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> LastName.of(name));
        assertEquals("Last name cannot be empty", illegalArgumentException.getMessage());
    }
}