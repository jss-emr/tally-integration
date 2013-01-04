package org.jss.mocks;

import org.jss.http.client.HttpClient;
import org.jss.tally.utils.TallyResponseMother;

public class MockHttpClient extends HttpClient {

    private RuntimeException exception = null;
    private String result = TallyResponseMother.successfulTallyResponse();

    @Override
    public String post(String url, String formPostData) {
        if (exception != null) throw exception;
        return result;
    }

    public void throwException(RuntimeException exception) {
        this.exception = exception;
    }

    public void setResult(String result) {

        this.result = result;
    }

}
