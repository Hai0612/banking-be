package com.core.common.dto;

import java.util.Optional;

/**
 * An immutable data carrier for device information extracted from request headers.
 * Using Optional ensures null-safety for potentially missing headers.
 *
 * @param deviceId   The unique identifier of the device.
 * @param deviceName The name of the device (e.g., "John's iPhone 15 Pro").
 * @param osVersion  The operating system version (e.g., "iOS 18.0").
 * @param ipAddress  The IP address of the client making the request.
 */
public record DeviceDetails(
    Optional<String> deviceId,
    Optional<String> deviceName,
    Optional<String> osVersion,
    Optional<String> ipAddress,
    Optional<String> fcmToken) {}