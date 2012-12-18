package org.jss.tally.web.controller;

import junit.framework.TestCase;
import org.jss.tally.domain.Ledger;
import org.jss.tally.service.TallyLedgerService;
import org.jss.test.utils.MVCTestUtils;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


public class TallyControllerTest extends TestCase {

    TallyController controller;
    @Mock
    TallyLedgerService tallyLedgerService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }
    //@Test
    public void testshouldCallLedgerServiceWithParameters() throws Exception {
        controller = new TallyController(tallyLedgerService);

        String name = "RamSingh";
        String patientId = "123456";
        String company = "JSS";
        MVCTestUtils.mockMvc(controller).perform(post("/tally/patient/create").param("patientName", name).
                param("patientId", patientId).param("company", company)).andExpect(status().isOk());

        ArgumentCaptor<Ledger> argument = ArgumentCaptor.forClass(Ledger.class);
        verify(tallyLedgerService).createLedger(argument.capture());

        assertEquals(patientId,argument.getValue().getPatientId());
        assertEquals(name,argument.getValue().getPatientName());
        assertEquals(company,argument.getValue().getCompany());

    }
}
