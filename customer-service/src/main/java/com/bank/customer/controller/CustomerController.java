package com.bank.customer.controller;

import com.bank.customer.dto.request.CreateCustomerRequest;
import com.bank.customer.dto.request.UpdateCustomerRequest;
import com.bank.customer.dto.response.CustomerResponse;
import com.bank.customer.service.CustomerService;
import com.core.common.dto.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer API", description = "Customer management APIs")
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @SecurityRequirement(name = "internalApiKey")
    @Operation(summary = "Get all customers")
    @GetMapping
    public ResponseEntity<ApiResponseFormat<List<CustomerResponse>>> getAll() {
        List<CustomerResponse> responses = customerService.getAll();
        return ResponseEntity.ok(ApiResponseFormat.success(responses));
    }

    @SecurityRequirement(name = "internalApiKey")
    @Operation(summary = "Create customer")
    @PostMapping
    public ResponseEntity<ApiResponseFormat<CustomerResponse>> create(
            @Valid @RequestBody CreateCustomerRequest request
    ) {

        CustomerResponse response =
                customerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseFormat.success(
                                "Customer created successfully",
                                response
                        )
                );
    }
    @SecurityRequirement(name = "internalApiKey")
    @Operation(summary = "Get customer by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<CustomerResponse>> getById(
            @PathVariable Long id
    ) {

        CustomerResponse response =
                customerService.get(id);

        return ResponseEntity.ok(
                ApiResponseFormat.success(response)
        );
    }
    @SecurityRequirement(name = "internalApiKey")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<CustomerResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
    ) {

        CustomerResponse response =
                customerService.update(id, request);

        return ResponseEntity.ok(
                ApiResponseFormat.success(
                        "Customer updated successfully",
                        response
                )
        );
    }


}