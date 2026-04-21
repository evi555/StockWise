package com.deom.marketdataservice.dto;

import com.deom.marketdataservice.model.Signal;
import lombok.Data;

@Data
public class ResponseGeneralDto {
    private String symbol;
    private double currentPrice;
    private double sma20;
    private double sma50;
    private Boolean aboveSma20;
    private Boolean aboveSma50;
    private Boolean sma20AboveSma50;
    private Signal signal;
    private int score;
}
