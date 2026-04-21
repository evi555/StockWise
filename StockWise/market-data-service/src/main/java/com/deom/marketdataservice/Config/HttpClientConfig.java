package com.deom.marketdataservice.Config;

// package: config

import com.deom.marketdataservice.Client.AlphavantageApi;
import com.deom.marketdataservice.Client.FinnhubApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    public FinnhubApi finnhubApi(
            @Value("${finnhub.base-url}") String baseUrl) {

        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)

                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(restClient))
                        .build();

        return factory.createClient(FinnhubApi.class);
    }

    @Bean
    public AlphavantageApi alphavantageApi(
            @Value("${alphavantage.base-url}") String baseUrl) {

        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)

                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(restClient))
                        .build();

        return factory.createClient(AlphavantageApi.class);
    }
}