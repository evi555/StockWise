package com.deom.marketdataservice.Controller;

import com.deom.marketdataservice.Service.analysisService;
import com.deom.marketdataservice.Service.dataService;
import com.deom.marketdataservice.dto.ErrorDto;
import com.deom.marketdataservice.dto.ResponseGeneralDto;
import com.deom.marketdataservice.dto.ResponseQutoeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@AllArgsConstructor
public class dataController {

 private final dataService dataService;
 private final analysisService analysisService;

    @GetMapping("/{symbol}")
    public ResponseEntity<?> GetQuoteBySymbol(@PathVariable String symbol){
        ResponseQutoeDto responseQutoeDto = dataService.makeQute(symbol);
        if (responseQutoeDto!=null){
            return ResponseEntity.ok(responseQutoeDto);
        }
        else {
            return ResponseEntity.status(404).body(new ErrorDto("Company not found",404));
        }

    }
    @GetMapping("/history/{symbol}")
    public double GetHistoryBySymbol(@PathVariable String symbol){
        dataService.executeHistory(symbol);
       return analysisService.CalcualteSma20BySymbol(symbol);
    }
    @GetMapping("/history/sma50/{symbol}")
    public double GetHistoryyBySymbol(@PathVariable String symbol){
        dataService.executeHistory(symbol);
        return analysisService.CalcualteSma50BySymbol(symbol);
    }

    @GetMapping("/analysis/{symbol}")
    public ResponseEntity<?>GetAnalysisBySymbol(@PathVariable String symbol){
        ResponseGeneralDto responseGeneralDto = analysisService.presentAdviceBySymbol(symbol);

        if (responseGeneralDto!=null){
            return ResponseEntity.ok(responseGeneralDto);
        }
        else {
            return ResponseEntity.status(404).body(new ErrorDto("Company not found",404));
        }


    }
}
