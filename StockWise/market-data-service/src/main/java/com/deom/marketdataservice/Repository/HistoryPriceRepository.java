package com.deom.marketdataservice.Repository;

import com.deom.marketdataservice.model.HisotricalPricesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryPriceRepository extends JpaRepository<HisotricalPricesModel,Long> {
    HisotricalPricesModel findByCompanySymbol(String CompanySymbol);

    double findClosingPriceByCompanySymbol(String symbol);

    double findClosingPriceById(String symbol,Long id);

    List<HisotricalPricesModel> findTop20ByCompanySymbolOrderByTradingDateDesc(String symbol);

    List<HisotricalPricesModel> findTop50ByCompanySymbolOrderByTradingDateDesc(String symbol);


    HisotricalPricesModel findByTradingDate(String key);

    double findVolumeByTradingDate(String currentDate);

    double findVolumeByCompanySymbolAndTradingDate(String symbol, String currentDate);

    HisotricalPricesModel findByCompanySymbolAndTradingDate(String symbol, String key);
}
