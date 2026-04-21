package com.deom.marketdataservice.dto;

import lombok.Data;

@Data
public class ResponseHistoryDto {
    private String date;
    private String volume;
    private String companySymbol;
    private String closing_price;
}
