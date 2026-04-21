package com.deom.marketdataservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "historical_prices")
@NoArgsConstructor
@Data
public class HisotricalPricesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String tradingDate;
    private double volume;
    private String companySymbol;
    private double closingPrice;
}
