package com.deom.marketdataservice.Service;

import com.deom.marketdataservice.Repository.HistoryPriceRepository;
import com.deom.marketdataservice.Repository.QouteRepository;
import com.deom.marketdataservice.dto.ResponseGeneralDto;
import com.deom.marketdataservice.model.HisotricalPricesModel;
import com.deom.marketdataservice.model.Signal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class analysisService {
   // private final dataService dataService;
    private final QouteRepository qouteRepository;
    private final HistoryPriceRepository HistoryPriceRepository;

    public ResponseGeneralDto presentAdviceBySymbol(String symbol){
        int score = calculateScore(symbol);
        double currentPrice = qouteRepository.findCurrentPriceByCompanySymbol(symbol);
        double sma20 = CalcualteSma20BySymbol(symbol);
        double sma50 = CalcualteSma50BySymbol(symbol);
        ResponseGeneralDto responseGeneralDto = new ResponseGeneralDto();
        responseGeneralDto.setSymbol(symbol);
        responseGeneralDto.setCurrentPrice(currentPrice);
        responseGeneralDto.setScore(score);
        responseGeneralDto.setSma20(sma20);
        responseGeneralDto.setSma50(sma50);
        responseGeneralDto.setAboveSma20(currentPrice>sma20);
        responseGeneralDto.setAboveSma50(currentPrice>sma50);
        responseGeneralDto.setSma20AboveSma50(sma20>sma50);
        responseGeneralDto.setSignal(calculateSignal(score));

        return responseGeneralDto;

    }
    public Signal calculateSignal(int score){
        if(score>=80){
            return Signal.BULLISH;
        }
        else if(score>=60){
            return Signal.WATCH;
        }
        else  if(score>=40){
            return Signal.NEUTRAL;
        }
        else {
            return Signal.WEAK;

        }


    }
    public int calculateScore(String symbol){
        String currentDate = String.valueOf(LocalDate.now());
        int grade=0;
        double current_price = qouteRepository.findCurrentPriceByCompanySymbol(symbol);
        double sma20 = CalcualteSma20BySymbol(symbol);
        double sma50 = CalcualteSma50BySymbol(symbol);
        double currentVolume = HistoryPriceRepository.findVolumeByCompanySymbolAndTradingDate(symbol,currentDate);
        double avgVolume20=CalcualteavgVolume20(symbol);
        double volatility = calcualteVolatility(symbol);

        if(volatility<3&& volatility>=1.5){
            grade = grade + 10;

        }
        if (volatility<1.5){
            grade = grade + 20;
        }

        if(currentVolume>avgVolume20){
            grade = grade + 20;
        }


        if(current_price>sma20){
            grade = grade + 20;
        }
        if(current_price>sma50){
            grade = grade + 20;
        }
        if(sma20>sma50){
            grade = grade + 20;
        }

        return grade;
    }
    public double calcualteVolatility(String symbol){
        List<HisotricalPricesModel> list = HistoryPriceRepository.findTop20ByCompanySymbolOrderByTradingDateDesc(symbol);

        return IntStream.range(1, list.size())
                .mapToDouble(i ->
                        Math.abs(
                                (list.get(i).getClosingPrice() - list.get(i - 1).getClosingPrice())
                                        / list.get(i - 1).getClosingPrice()
                        ) * 100
                )
                .average()
                .orElse(0);
    }

    public double CalcualteavgVolume20(String symbol){
        List<HisotricalPricesModel> list = HistoryPriceRepository.findTop20ByCompanySymbolOrderByTradingDateDesc(symbol);
        return   list.stream().mapToDouble(HisotricalPricesModel::getVolume).average().orElse(0);

    }
    public double CalcualteSma20BySymbol(String symbol){
        double sma20 = 0;

        List<HisotricalPricesModel> list = HistoryPriceRepository.findTop20ByCompanySymbolOrderByTradingDateDesc(symbol);
        sma20 = list.stream().mapToDouble(HisotricalPricesModel::getClosingPrice).average().orElse(0);
        return sma20;


    }
    public double CalcualteSma50BySymbol(String symbol){
        double sma50 = 0;

        List<HisotricalPricesModel> list = HistoryPriceRepository.findTop50ByCompanySymbolOrderByTradingDateDesc(symbol);
        sma50 = list.stream().mapToDouble(HisotricalPricesModel::getClosingPrice).average().orElse(0);
        return sma50;


    }
}
