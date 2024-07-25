package com.web.megamart.dto;

import com.web.megamart.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,

        @NotNull(message = "customer firstname is required")
        String firstName,
        @NotNull(message = "customer lastname is required")
        String lastName,
        @NotNull(message = "customer email is required")
        @Email(message = "please provide a valid email address")
        String email,
        Address address
) {
}
