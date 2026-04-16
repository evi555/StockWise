package com.ecommerce.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Value("${server.port}")
    private String port;
    private final String instanceId = java.util.UUID.randomUUID().toString();

    @GetMapping("/instance-info")
    public String getInstance(){
        System.out.println("Request Received at instance running on port:" + port);
        return "Instance Served by Port: " + port + ". Instance ID: " + instanceId;
    }
}
