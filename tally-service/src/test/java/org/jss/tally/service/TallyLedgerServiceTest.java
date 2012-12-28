package org.jss.tally.service;

import junit.framework.TestCase;
import org.jss.http.client.HttpClient;
import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Properties;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class TallyLedgerServiceTest {

    @Mock
    RequestBuilder requestBuilder;
    TallyLedgerService ledgerService;

    @Mock
    HttpClient httpClient;

    @Mock
    Properties tallyServiceProperties;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldCallTallyEndpointWithNewLedgerRequest()  {
      ledgerService = new TallyLedgerService(requestBuilder, httpClient,tallyServiceProperties);
        Ledger ledger = new Ledger("patient Name", "patient id", "company");
        String tallyXmlRequest = new String("The xml created");

        when(requestBuilder.buildNewLedgerRequest(ledger)).thenReturn(tallyXmlRequest);
        String tallyUrl = "http://tallyService/tally";
        when(tallyServiceProperties.getProperty("tallyUrl")).thenReturn(tallyUrl);

        ledgerService.createLedger(ledger);
        verify(httpClient).post(tallyUrl, tallyXmlRequest);

    }
}
