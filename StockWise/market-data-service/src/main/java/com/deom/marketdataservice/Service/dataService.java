package com.deom.marketdataservice.Service;

import com.deom.marketdataservice.Client.AlphavantageApi;
import com.deom.marketdataservice.Client.FinnhubApi;
//import com.deom.marketdataservice.Client.FinnubClient;
import com.deom.marketdataservice.Repository.HistoryPriceRepository;
import com.deom.marketdataservice.Repository.QouteRepository;
import com.deom.marketdataservice.dto.*;
import com.deom.marketdataservice.model.HisotricalPricesModel;
import com.deom.marketdataservice.model.QouteModel;
import com.deom.marketdataservice.model.Signal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class dataService {
    private final FinnhubApi finnubClient;
    private final int SMA20_SIZE=20;
    private final int SMA50_SIZE=50;
    private final AlphavantageApi alphavantageApi;
    private final QouteRepository qouteRepository;
    private final HistoryPriceRepository HistoryPriceRepository;
    @Value("${finnhub.api-key}") private String finnhub_token_key;
    @Value("${alphavantage.api-key}") private String alphavantage_token_key;
    private final String Time_ser="TIME_SERIES_DAILY";



    public void executeHistory(String symbol){
       AlphaVantageResponseDto alphaVantageResponseDto;
        try{
            alphaVantageResponseDto = alphavantageApi.getDaily(Time_ser,symbol,alphavantage_token_key);
            String only = alphavantageApi.getDailyString(Time_ser,symbol,alphavantage_token_key);
            System.out.println(only);
        }
        catch (Exception e) {
            throw  new RuntimeException(e);
        }
       // HisotricalPricesModel hisotricalPricesModel = new HisotricalPricesModel();

        ConvertFromHistoryDtoToModel(alphaVantageResponseDto,symbol);


    }


    public void ConvertFromHistoryDtoToModel(AlphaVantageResponseDto alphaVantageResponseDto,String symbol) {
        Map<String, AlphaDailyItemDto> timeSeriesDaily  = alphaVantageResponseDto.getTimeSeriesDaily();
        for (Map.Entry<String, AlphaDailyItemDto> entry : timeSeriesDaily.entrySet()){

            if(HistoryPriceRepository.findByCompanySymbolAndTradingDate(symbol,entry.getKey())!=null){
                continue;
            }
            HisotricalPricesModel hisotricalPricesModel = new HisotricalPricesModel();
            hisotricalPricesModel.setTradingDate(entry.getKey());
            hisotricalPricesModel.setClosingPrice(Double.parseDouble(entry.getValue().getClosing_price()));
            hisotricalPricesModel.setCompanySymbol(symbol);
            hisotricalPricesModel.setVolume(Double.parseDouble(entry.getValue().getVolume()));
            HistoryPriceRepository.save(hisotricalPricesModel);
        }



    }

    public ResponseQutoeDto makeQute(String symbol){
        FinnhubResponseQuoteDto finnhubResponseQuoteDto;
        try{
             finnhubResponseQuoteDto = finnubClient.getQuote(symbol,finnhub_token_key);
        }
        catch (Exception e) {
            throw  new RuntimeException(e);
        }



        QouteModel findmodel = qouteRepository.findByCompanySymbol(symbol);
        // in case company is not in DB
        if(findmodel==null){
            QouteModel qouteModel =  new QouteModel();
            ConvertFromDtoToModel(finnhubResponseQuoteDto,qouteModel,symbol);
            ResponseQutoeDto responseQutoeDto = new ResponseQutoeDto();
            return ConvertFromModelToDto(responseQutoeDto,qouteModel);
        }
        // in case company is in DB
        else {
            ConvertFromDtoToModel(finnhubResponseQuoteDto,findmodel,symbol);
            ResponseQutoeDto responseQutoeDto = new ResponseQutoeDto();
            return ConvertFromModelToDto(responseQutoeDto,findmodel);

        }


    }

    private ResponseQutoeDto ConvertFromModelToDto(ResponseQutoeDto responseQutoeDto, QouteModel qouteModel) {
        responseQutoeDto.setCurrentPrice(qouteModel.getCurrentPrice());
        responseQutoeDto.setChange(qouteModel.getChange());
        responseQutoeDto.setHighPriceOfTheDay(qouteModel.getHighPriceOfTheDay());
        responseQutoeDto.setPercentChange(qouteModel.getPercentChange());
        responseQutoeDto.setLowPriceOfTheDay(qouteModel.getLowPriceOfTheDay());
        responseQutoeDto.setOpenPriceOfTheDay(qouteModel.getOpenPriceOfTheDay());
        responseQutoeDto.setPreviousClosePrice(qouteModel.getPreviousClosePrice());
        responseQutoeDto.setCompanySymbol(qouteModel.getCompanySymbol());
        return responseQutoeDto;
    }

    private void ConvertFromDtoToModel(FinnhubResponseQuoteDto finnhubResponseQuoteDto,QouteModel qouteModel,String symbol) {
        qouteModel.setCurrentPrice(finnhubResponseQuoteDto.getCurrentPrice());
        qouteModel.setChange(finnhubResponseQuoteDto.getChange());
        qouteModel.setHighPriceOfTheDay(finnhubResponseQuoteDto.getHighPriceOfTheDay());
        qouteModel.setPercentChange(finnhubResponseQuoteDto.getPercentChange());
        qouteModel.setLowPriceOfTheDay(finnhubResponseQuoteDto.getLowPriceOfTheDay());
        qouteModel.setOpenPriceOfTheDay(finnhubResponseQuoteDto.getOpenPriceOfTheDay());
        qouteModel.setPreviousClosePrice(finnhubResponseQuoteDto.getPreviousClosePrice());
        qouteModel.setCompanySymbol(symbol);
        qouteRepository.save(qouteModel);


    }
}
