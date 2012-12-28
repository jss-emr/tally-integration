package org.jss.tally.web.controller.it;

import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.verify;

@ContextConfiguration("classpath:applicationContext-Web.xml")
public class TallyControllerIntegrationIT {
//    @Test
//    public void shouldboo() {
//
//
//        new RestTemplate().getForObject("http://localhost:8080/tally-webservice/tally/patient/create?patientName=123&patientId=something&company=TRIANZ DEMO DATA);
//
//        new RestTemplate().postForLocation("http://localhost:8080/tally-webservice/tally/patient/create", )
//
//
//
//        controller = new TallyController(tallyLedgerService);
//
//        String name = "RamSingh" + DateTime.now().getMillis();
//        String patientId = "123456";
//        String company = "JSS";
//        MVCTestUtils.mockMvc(controller).perform(post("/tally/patient/create").param("patientName", name).
//                param("patientId", patientId).param("company", company)).andExpect(status().isOk());
//
//        ArgumentCaptor<Ledger> argument = ArgumentCaptor.forClass(Ledger.class);
//
//        assertEquals(patientId, argument.getValue().getPatientId());
//        assertEquals(name, argument.getValue().getPatientName());
//        assertEquals(company, argument.getValue().getCompany());
//
//    }
}
