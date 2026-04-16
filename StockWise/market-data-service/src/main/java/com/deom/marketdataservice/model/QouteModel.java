package com.deom.marketdataservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity(name="qoutes")
@NoArgsConstructor
@Data
public class QouteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companySymbol;

    private double currentPrice;

    private double change;

    private double percentChange;

    private double highPriceOfTheDay;

    private double lowPriceOfTheDay;

    private double openPriceOfTheDay;

    private double previousClosePrice;
}
