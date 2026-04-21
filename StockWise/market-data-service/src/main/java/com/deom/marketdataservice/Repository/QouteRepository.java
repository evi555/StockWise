package com.deom.marketdataservice.Repository;

import com.deom.marketdataservice.model.QouteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QouteRepository extends JpaRepository<QouteModel,Long> {
    QouteModel findByCompanySymbol(String CompanySymbol);

    double findCurrentPriceByCompanySymbol(String symbol);
}
