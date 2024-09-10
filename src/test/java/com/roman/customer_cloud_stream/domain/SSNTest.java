package com.roman.customer_cloud_stream.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SSNTest {

    @Test
    void shouldCreateSSN(){
        Integer num = 324534234;

        SSN ssn = SSN.of(num);

        assertNotNull(ssn);
        assertEquals(num, ssn.ssn());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSsnIsNull(){
        Integer num = null;

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> SSN.of(num));

        assertEquals("SSN could not be null", nullPointerException.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSsnIsNotExactly9Digits(){
        Integer num = 23542;

        IllegalArgumentException nullPointerException = assertThrows(IllegalArgumentException.class, () -> SSN.of(num));

        assertEquals("The ssn must have 9 characters", nullPointerException.getMessage());
    }
}