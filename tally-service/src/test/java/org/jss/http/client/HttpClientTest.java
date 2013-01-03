package org.jss.http.client;

import junit.framework.Assert;
import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RequestBuilder requestBuilder;

    @Test
    public void shouldUseRestTemplateAndMakeAPostRequest() {
        String requestXml = createRequest();
        String url = "http://172.18.2.2:9000";

        new HttpClient(restTemplate).post(url, requestXml);

        verify(restTemplate).postForObject(url, requestXml, String.class);
    }

    @Test
    public void shouldReturnResponseFromPostRequest() {
        String requestXml = createRequest();
        String url = "http://172.18.2.2:9000";
        String tallyResponse = "Tally response";

        when(restTemplate.postForObject(url, requestXml, String.class)).thenReturn(tallyResponse);

        String response = new HttpClient(restTemplate).post(url, requestXml);

        Assert.assertEquals(response, tallyResponse);
    }

    private String createRequest() {
        Ledger ledger = new Ledger("name3","abcd1234","TRIANZ DEMO DATA");

        return requestBuilder.buildNewLedgerRequest(ledger);
    }
}
