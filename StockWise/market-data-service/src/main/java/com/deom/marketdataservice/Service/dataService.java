package com.deom.marketdataservice.Service;

import com.deom.marketdataservice.Client.FinnhubApi;
//import com.deom.marketdataservice.Client.FinnubClient;
import com.deom.marketdataservice.Repository.QouteRepository;
import com.deom.marketdataservice.dto.ResponseQutoeDto;
import com.deom.marketdataservice.dto.FinnhubResponseQuoteDto;
import com.deom.marketdataservice.model.QouteModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class dataService {
    private final FinnhubApi finnubClient;
    private final QouteRepository qouteRepository;
    @Value("${finnhub.api-key}") private String api_token_key;
     ;


    public ResponseQutoeDto makeQute(String symbol){
        FinnhubResponseQuoteDto finnhubResponseQuoteDto;
        try{
             finnhubResponseQuoteDto = finnubClient.getQuote(symbol,api_token_key);
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
