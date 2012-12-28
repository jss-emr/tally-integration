package org.jss.http.client;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpClient {
    Logger logger = Logger.getLogger(HttpClient.class);

    public String post(String url, String formPostData) {
        try {
            logger.debug("Post Data: " + formPostData);
            URL serviceUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serviceUrl.openConnection();
            conn.setRequestMethod(RequestMethod.POST.name());
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(formPostData);
            wr.flush();

            String output = readOutput(conn);
            logger.debug("Post Data output: " + output);
            wr.close();
            return output;
        } catch (Exception e) {
            logger.error("Could not post  to " + url, e);
            logger.error("Post data: " + formPostData);
            throw new RuntimeException("Could not post message", e);
        }
    }

    private String readOutput(HttpURLConnection conn) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            output.append(line);
        }
        rd.close();
        return output.toString();
    }
}
