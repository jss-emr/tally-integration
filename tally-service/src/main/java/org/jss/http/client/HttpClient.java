package org.jss.http.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HttpClient {
    private RestTemplate restTemplate;
    Logger logger = Logger.getLogger(HttpClient.class);

    @Autowired
    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void execute(String url, Object request) {
        restTemplate.postForLocation(url, request);
    }
}
