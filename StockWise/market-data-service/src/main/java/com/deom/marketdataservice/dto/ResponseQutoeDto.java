package com.deom.marketdataservice.dto;

import lombok.Data;

@Data
public class ResponseQutoeDto {

    private double currentPrice;

    private String companySymbol;

    private double change;

    private double percentChange;

    private double highPriceOfTheDay;

    private double lowPriceOfTheDay;

    private double openPriceOfTheDay;

    private double previousClosePrice;

}
