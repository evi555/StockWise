package com.ecommerce.consumer.RestTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/rest-template")
@RequiredArgsConstructor
public class RestTemplateController {
    private final RestTemplateClient restTemplateClient;
    @GetMapping("/instance")
    public String getInstance(){
        return restTemplateClient.getInstance();
    }

}
