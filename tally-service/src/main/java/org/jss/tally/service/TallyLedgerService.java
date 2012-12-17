package org.jss.tally.service;

import org.jss.http.client.HttpClient;
import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.Property;
import org.springframework.stereotype.Component;

@Component
public class TallyLedgerService {

    private RequestBuilder requestBuilder;
    private HttpClient httpClient;

    @Qualifier("tallyServiceProperties")
    private Property tallyServiceProperties;

    @Autowired
    public TallyLedgerService(RequestBuilder requestBuilder, HttpClient httpClient){
        this.requestBuilder = requestBuilder;
        this.httpClient = httpClient;
    }

    public void createLedger(Ledger ledger) {
        String requestXml = requestBuilder.buildNewLedgerRequest(ledger);
        String url = "http://localhost:9000";
        httpClient.execute(url,requestXml);
    }
}
