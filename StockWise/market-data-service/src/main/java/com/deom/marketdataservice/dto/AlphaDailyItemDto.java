package com.deom.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlphaDailyItemDto {

    @JsonProperty("4. close")
    private String closing_price;
    @JsonProperty("5. volume")
    private String volume;
}
