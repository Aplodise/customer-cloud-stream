package com.roman.customer_cloud_stream.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {
    @Test
    void shouldCreateBirthDate(){
        LocalDate date = LocalDate.of(2020, 10, 10);

        BirthDate birthDate = BirthDate.of(date);
        assertNotNull(birthDate);
        assertEquals(date, birthDate.birthDate());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenBirthDateIsNull(){
        LocalDate date = null;

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> BirthDate.of(date));

        assertEquals("The birthdate cannot be null", nullPointerException.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenBirthDateIsInTheFuture(){
        LocalDate date = LocalDate.now().plusDays(1L);

        IllegalArgumentException nullPointerException = assertThrows(IllegalArgumentException.class, () -> BirthDate.of(date));

        assertEquals("The birthdate should be in the past", nullPointerException.getMessage());
    }
}