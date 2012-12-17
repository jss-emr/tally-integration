package org.jss.tally.builder;

import junit.framework.TestCase;
import org.jss.tally.domain.Ledger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContextTallyService.xml")
public class RequestBuilderTest extends TestCase {

    @Autowired
    RequestBuilder requestBuilder;

    @Test
    public void shouldCreateNewLedgerRequestWithPatientDataPopulated() throws Exception {
        Ledger ledger = new Ledger("RamSingh","abcd1234","JSS");
        String requestXml = requestBuilder.buildNewLedgerRequest(ledger);
        assertEquals("<ENVELOPE>\n" +
                "    <HEADER>\n" +
                "        <TALLYREQUEST>Import Data</TALLYREQUEST>\n" +
                "    </HEADER>\n" +
                "    <BODY>\n" +
                "    <IMPORTDATA>\n" +
                "        <REQUESTDESC>\n" +
                "            <REPORTNAME>All Masters</REPORTNAME>\n" +
                "            <STATICVARIABLES>\n" +
                "                <SVCURRENTCOMPANY>JSS</SVCURRENTCOMPANY>\n" +
                "            </STATICVARIABLES>\n" +
                "        </REQUESTDESC>\n" +
                "        <REQUESTDATA>\n" +
                "            <!-- Create Ledger for Patient -->\n" +
                "            <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
                "                <LEDGER NAME=\"RamSingh\" ACTION=\"Create\">\n" +
                "                    <NAME.LIST>\n" +
                "                        <NAME>RamSingh</NAME>\n" +
                "                        <ALIAS>abcd1234</ALIAS>\n" +
                "                    </NAME.LIST>\n" +
                "                    <PARENT>Sundry Debtors</PARENT>\n" +
                "                    <ISBILLWISEON>Yes</ISBILLWISEON>\n" +
                "                    <AFFECTSSTOCK>Yes</AFFECTSSTOCK>\n" +
                "                    <OPENINGBALANCE>0</OPENINGBALANCE>\n" +
                "                    <USEFORVAT>NO</USEFORVAT>\n" +
                "                    <TAXCLASSIFICATIONNAME></TAXCLASSIFICATIONNAME>\n" +
                "                </LEDGER>\n" +
                "            </TALLYMESSAGE>\n" +
                "        </REQUESTDATA>\n" +
                "    </IMPORTDATA>\n" +
                "    </BODY>\n" +
                "</ENVELOPE>\n".trim(),
                requestXml.trim());


    }
}
