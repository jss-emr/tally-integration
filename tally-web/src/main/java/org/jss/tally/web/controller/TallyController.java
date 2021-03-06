package org.jss.tally.web.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jss.tally.domain.Ledger;
import org.jss.tally.service.TallyLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody ResponseEntity<String> createLedger(@RequestParam String patientName, @RequestParam String patientId, @RequestParam(required = false) String company) throws IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(StringUtils.isBlank(patientName) || StringUtils.isBlank(patientId)) {
            return new ResponseEntity<String>("Either patientName or patientId is blank",responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR)  ;
        }

        ledgerService.createLedger(new Ledger(patientName,patientId,company));

        return new ResponseEntity<String>(responseHeaders,HttpStatus.OK)  ;
    }

  }


