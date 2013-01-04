package org.jss.tally.domain;

import org.jss.tally.utils.TallyResponseMother;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TallyResponseTest {
    @Test
    public void shouldCreateTallyResponseForSuccessfulResponse() {
        TallyResponse response = new TallyResponse(TallyResponseMother.successfulTallyResponse());
        assertFalse(response.isCreated());
        assertTrue(response.isAltered());
        assertTrue(response.isSuccess());
    }

    @Test
    public void shouldCreateTallyResponseForFailureResponse() {
        TallyResponse response = new TallyResponse(TallyResponseMother.unsuccessfulTallyResponse());

        assertFalse(response.isCreated());
        assertFalse(response.isAltered());
        assertFalse(response.isSuccess());
    }
}
