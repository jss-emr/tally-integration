package org.jss.tally.web.controller;

import org.apache.log4j.Logger;
import org.jss.tally.domain.Ledger;
import org.jss.tally.service.TallyLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/tally/**")
public class TallyController {

    TallyLedgerService ledgerService;

    private static Logger logger =Logger.getLogger("TallyService") ;

    @Autowired
    public TallyController(TallyLedgerService ledgerService){
       this.ledgerService = ledgerService;
    }

    @RequestMapping(value = "/patient/create", method = RequestMethod.POST,headers="Accept=application/json")
    public @ResponseBody ResponseEntity<String> createLedger(HttpEntity<String> requestEntity,@RequestBody Ledger ledger) throws IOException {
        logger.info(requestEntity.getBody());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        ledgerService.createLedger(ledger);

        return new ResponseEntity<String>(responseHeaders,HttpStatus.OK)  ;
    }

  }


