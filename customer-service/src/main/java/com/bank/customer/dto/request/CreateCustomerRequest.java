package com.bank.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(

        @NotNull
        Long userId,

        @NotBlank
        @Size(max = 100)
        String fullName,

        @NotBlank
        @Email
        String email,

        @Size(max = 20)
        String phone

) {
}