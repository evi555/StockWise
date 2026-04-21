package com.deom.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class AlphaVantageResponseDto {
    @JsonProperty("Time Series (Daily)")
    private Map<String,AlphaDailyItemDto> timeSeriesDaily;
}
