package com.megamart.payment.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
    String id,
    @NotNull(message = "firstname is required")
    String firstName,
    String lastName,
    @NotNull(message = "email address is required")
    @Email(message = "email address is not valid")
    String email
) {
}
