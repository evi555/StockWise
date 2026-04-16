package com.deom.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinnhubResponseQuoteDto {
    @JsonProperty("c")
    private double currentPrice;
    @JsonProperty("d")
    private double change;
    @JsonProperty("dp")
    private double percentChange;
    @JsonProperty("h")
    private double highPriceOfTheDay;
    @JsonProperty("l")
    private double lowPriceOfTheDay;
    @JsonProperty("o")
    private double openPriceOfTheDay;
    @JsonProperty("pc")
    private double previousClosePrice;




}
