package com.deom.marketdataservice.Client;

import com.deom.marketdataservice.dto.FinnhubResponseQuoteDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface FinnhubApi {

    @GetExchange("/quote")
    FinnhubResponseQuoteDto getQuote(@RequestParam String symbol, @RequestParam String token);


}