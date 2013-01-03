package org.jss.mocks;

import org.jss.http.client.HttpClient;

public class MockHttpClient extends HttpClient {

    private RuntimeException exception = null;

    @Override
    public String post(String url, String formPostData) {
        if (exception != null) throw exception;
        return "posted";
    }

    public void throwException(RuntimeException exception) {
        this.exception = exception;
    }

}
