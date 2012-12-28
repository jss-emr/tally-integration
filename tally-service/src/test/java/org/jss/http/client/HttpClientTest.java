package org.jss.http.client;

import org.jss.tally.builder.RequestBuilder;
import org.jss.tally.domain.Ledger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContextTallyService.xml")
public class HttpClientTest {

    @Autowired
    private RequestBuilder requestBuilder;

    @Test
    public void shouldMakePostAndReturnResponse() {
        String requestXml = createRequest();

        String url = "http://172.18.2.2:9000";

        new HttpClient().post(url, requestXml);
    }

    private String createRequest() {
        Ledger ledger = new Ledger("name3","abcd1234","TRIANZ DEMO DATA");

        return requestBuilder.buildNewLedgerRequest(ledger);
    }
}
