package com.deom.marketdataservice.Client;

import com.deom.marketdataservice.dto.AlphaDailyItemDto;
import com.deom.marketdataservice.dto.AlphaVantageResponseDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface AlphavantageApi {
    @GetExchange
    AlphaVantageResponseDto getDaily(@RequestParam String function, @RequestParam String symbol , @RequestParam String apikey);
    @GetExchange
    String getDailyString(@RequestParam String function, @RequestParam String symbol , @RequestParam String apikey);
}
