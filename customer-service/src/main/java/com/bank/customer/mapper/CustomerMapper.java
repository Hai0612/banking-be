package com.bank.customer.mapper;

import com.bank.customer.dto.response.CustomerResponse;
import com.bank.customer.entity.Customer;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerResponse toResponse(
            Customer customer
    ) {

        return new CustomerResponse(
                customer.getId(),
                customer.getUserId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
