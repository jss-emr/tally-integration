package org.jss.tally.service;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jss.http.client.HttpClient;
import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.jss.tally.utils.TallyResponseMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Properties;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TallyLedgerServiceTest {

    TallyLedgerService ledgerService;
    @Mock
    RequestBuilder requestBuilder;
    @Mock
    HttpClient httpClient;
    @Mock
    Properties tallyServiceProperties;

    @Test
    public void shouldCallTallyEndpointWithNewLedgerRequest() {
        ledgerService = new TallyLedgerService(requestBuilder, httpClient, tallyServiceProperties);
        Ledger ledger = new Ledger("patient Name", "patient id", "company");
        String tallyXmlRequest = new String("The xml created");
        String tallyUrl = "http://tallyService/tally";

        when(requestBuilder.buildNewLedgerRequest(ledger)).thenReturn(tallyXmlRequest);
        when(tallyServiceProperties.getProperty("tallyUrl")).thenReturn(tallyUrl);
        when(httpClient.post(tallyUrl,tallyXmlRequest)).thenReturn(TallyResponseMother.successfulTallyResponse());

        ledgerService.createLedger(ledger);
        verify(httpClient).post(tallyUrl, tallyXmlRequest);
    }

    @Test
    public void shouldDefaultTheCompanyIfNotProvided() {
        ledgerService = new TallyLedgerService(requestBuilder, httpClient, tallyServiceProperties);
        Ledger ledgerWithNullCompany = new Ledger("patient Name", "patient id", null);
        String defaultCompany = "defaultCompany";
        Ledger expectedLedger = new Ledger("patient Name", "patient id", defaultCompany);
        String tallyXmlRequest = new String("The xml created");
        String tallyUrl = "http://tallyService/tally";

        when(requestBuilder.buildNewLedgerRequest(argThat(new ValidLedger(expectedLedger)))).thenReturn(tallyXmlRequest);
        when(tallyServiceProperties.getProperty("tallyUrl")).thenReturn(tallyUrl);
        when(tallyServiceProperties.getProperty("defaultTallyCompany")).thenReturn(defaultCompany);
        when(httpClient.post(tallyUrl,tallyXmlRequest)).thenReturn(TallyResponseMother.successfulTallyResponse());

        ledgerService.createLedger(ledgerWithNullCompany);
        verify(httpClient).post(tallyUrl, tallyXmlRequest);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionForIncorrectResult() {
        ledgerService = new TallyLedgerService(requestBuilder, httpClient, tallyServiceProperties);
        Ledger ledger = new Ledger("patient Name", "patient id", "company");
        String tallyXmlRequest = new String("The xml created");

        when(requestBuilder.buildNewLedgerRequest(ledger)).thenReturn(tallyXmlRequest);
        String tallyUrl = "http://tallyService/tally";
        when(tallyServiceProperties.getProperty("tallyUrl")).thenReturn(tallyUrl);
        when(httpClient.post(tallyUrl,tallyXmlRequest)).thenReturn(TallyResponseMother.unsuccessfulTallyResponse());

        ledgerService.createLedger(ledger);
    }

    private class ValidLedger extends BaseMatcher<Ledger> {
        private Ledger expectedLedger;

        public ValidLedger(Ledger expectedLedger) {
            this.expectedLedger = expectedLedger;
        }

        @Override
        public boolean matches(Object item) {
            if (!item.getClass().equals(Ledger.class)) return false;
            Ledger ledger = (Ledger) item;
            return ledger.getPatientId().equals(expectedLedger.getPatientId()) &&
                    ledger.getPatientName().equals(expectedLedger.getPatientName()) &&
                    ledger.getCompany().equals(expectedLedger.getCompany());
        }

        @Override
        public void describeTo(Description description) {
        }
    }
}
