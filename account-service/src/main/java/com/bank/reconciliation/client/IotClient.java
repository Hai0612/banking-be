package com.bank.fraud.client;

import com.core.client.AuthorizedFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AuthorizedFeignClient(name = "iot-service", url = "${app.iot-service.base-url}")
public interface IotClient {
    @GetMapping("/api/v1/devices")
    String getDeviceInfo(@RequestParam String deviceId);

    default boolean isDeviceActivated(String deviceId) {
        try {
             return true;
        } catch (Exception e) {
            return false;
        }
    }
}
