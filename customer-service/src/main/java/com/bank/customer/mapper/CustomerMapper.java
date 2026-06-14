package com.core.common.mapper;

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
