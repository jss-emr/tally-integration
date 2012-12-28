package org.jss.tally.builder;

import org.jss.tally.domain.Ledger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContextTallyService.xml")
public class RequestBuilderTest {

    @Autowired
    RequestBuilder requestBuilder;

    @Test
    public void shouldCreateNewLedgerRequestWithPatientDataPopulated() throws Exception {
        Ledger ledger = new Ledger("RamSingh","abcd1234","JSS");
        String requestXml = requestBuilder.buildNewLedgerRequest(ledger);
        String requestXmlForComparison = requestXml.replace("\n", " ");

        assertTrue(requestXmlForComparison.matches(".*<\\s*NAME\\s*>\\s*" + ledger.getPatientName() + "\\s*<\\s*/\\s*NAME\\s*>.*"));
        assertTrue(requestXmlForComparison.matches(".*<\\s*LEDGER\\s*NAME\\s*=\\s*\"" + ledger.getPatientName() + "\".*"));
        assertTrue(requestXmlForComparison.matches(".*<SVCURRENTCOMPANY>\\s*" + ledger.getCompany()+ "\\s*</SVCURRENTCOMPANY>.*"));
        assertTrue(requestXmlForComparison.matches(".*<\\s*NAME\\s*>\\s*RamSingh\\s*<\\s*/\\s*NAME\\s*>.*"));
    }
}
