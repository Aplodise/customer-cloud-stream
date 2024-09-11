package com.roman.customer_cloud_stream.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(@NotNull @NotBlank String emailAddress) {
}
