package com.bank.customer.dto.response;

import java.time.LocalDateTime;

public record CustomerResponse(

        Long id,

        Long userId,

        String fullName,

        String email,

        String phone,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}