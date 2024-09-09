package com.roman.customer_cloud_stream.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CustomerDTO(@NotBlank(message = "first name cannot be blank")
                          String firstName,
                          @NotBlank(message = "last name cannot be blank")
                          String lastName,
                          @NotNull(message = "the birthdate cannot be null")
                          @Past(message = "the birthdate should be in the past")
                          LocalDate birthDate,
                          @NotBlank(message = "email cannot be blank")
                          @Email(message = "please provide a valid email address")
                          String emailAddress,
                          @NotNull(message = "ssn should not be null")
                          Integer ssn) {
}
