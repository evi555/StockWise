package com.ecommerce.consumer.RestTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateClient {
    private final RestTemplate restTemplate;
    private final static String URL="http://localhost:8081/instance-info";

    public String getInstance(){
        return restTemplate.getForObject(URL,String.class);
    }
}
