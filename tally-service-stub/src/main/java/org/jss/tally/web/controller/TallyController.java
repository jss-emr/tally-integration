package org.jss.tally.web.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/tally/**")
public class TallyController {

    @RequestMapping(value = "/patient/create", method = RequestMethod.POST,headers="Accept=application/json")
    public @ResponseBody ResponseEntity<String> createLedger(@RequestParam String patientName, @RequestParam String patientId, @RequestParam(required = false) String company) throws IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(StringUtils.isBlank(patientName) || StringUtils.isBlank(patientId)) {
            return new ResponseEntity<String>("Either patientName or patientId is blank",responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR)  ;
        }

        return new ResponseEntity<String>(responseHeaders,HttpStatus.OK)  ;
    }

 }


