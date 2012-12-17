package org.jss.tally.service;

import org.jss.http.client.HttpClient;
import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TallyLedgerService {

    private RequestBuilder requestBuilder;
    private HttpClient httpClient;

    private Properties tallyServiceProperties;

    @Autowired
    public TallyLedgerService(RequestBuilder requestBuilder, HttpClient httpClient, @Qualifier("tallyServiceProperties") Properties tallyServiceProperties) {
        this.requestBuilder = requestBuilder;
        this.httpClient = httpClient;
        this.tallyServiceProperties = tallyServiceProperties;
    }

    public void createLedger(Ledger ledger) {
        String requestXml = requestBuilder.buildNewLedgerRequest(ledger);
        String url = (String) tallyServiceProperties.getProperty("tallyUrl");
        httpClient.execute(url,requestXml);
    }
}
