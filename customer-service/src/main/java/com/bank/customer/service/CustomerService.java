package com.bank.customer.service;

import com.bank.customer.dto.request.CreateCustomerRequest;
import com.bank.customer.dto.request.UpdateCustomerRequest;
import com.bank.customer.dto.response.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.mapper.CustomerMapper;
import com.bank.customer.repository.CustomerRepository;
import com.core.common.exception.BusinessException;
import com.core.common.exception.CoreErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse create(
            CreateCustomerRequest request
    ) {

        if (customerRepository.existsByEmail(
                request.email())) {

            throw new BusinessException(
                    CoreErrorCode.EMAIL_ALREADY_EXISTS
            );
        }

        Customer customer = Customer.builder()
                .userId(request.userId())
                .fullName(request.fullName())
                .email(request.email())
                .phone(request.phone())
                .build();

        customer = customerRepository.save(customer);

        log.info(
                "Customer created customerId={}",
                customer.getId()
        );

        return CustomerMapper.toResponse(customer);
    }

    @Transactional(readOnly = true)
    public CustomerResponse get(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(
                                () -> new BusinessException(
                                        CoreErrorCode.CUSTOMER_NOT_FOUND
                                )
                        );

        return CustomerMapper.toResponse(customer);
    }

    public CustomerResponse update(
            Long id,
            UpdateCustomerRequest request
    ) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(
                                () -> new BusinessException(
                                        CoreErrorCode.CUSTOMER_NOT_FOUND
                                )
                        );

        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());

        return CustomerMapper.toResponse(customer);
    }
}