package com.core.dto;

/**
 * A simple, immutable DTO for carrying details of a single field validation error.
 *
 * @param field   The name of the field that failed validation.
 * @param message The specific validation error message for that field.
 */
public record FieldErrorDetail(String field, String message) {}