package net.mossol;


import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Created by Amos.Doan.Mac on 2017. 12. 3..
 */
public class HttpConnection {
    private final static Logger logger = LoggerFactory.getLogger(HttpConnection.class);
    private final static HttpClient httpClient = HttpClientBuilder.create().build();
    private final static String ACCESS_TOKEN = "9hPQz4rrGETJtpFpfIrVxg4RY5jwezlrIT6l4TCCwNu7VBCqXan2Iwvq1AOEjJgPal3nZQdH/zmEr7bYeNkz3QcmTn25abpX234CjqH+9jusUvFnHHCbE8mIy3e6u4WUJ/wzssuxOYD7Ut3Pd+OwNwdB04t89/1O/w1cDnyilFU=";

    public boolean post(String uriStr, String payload) {

        try {
            URI uri = new URI(uriStr);
            HttpPost postRequest = new HttpPost(uri);

            String token = "Bearer " + ACCESS_TOKEN;
            postRequest.addHeader("Authorization", token);

            if (payload != null) {
                StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
                postRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                postRequest.setEntity(entity);
            }

            logger.debug("Token : {}", token);
            logger.debug("postRequest : {}", postRequest.toString());
            logger.debug("Header : {}", (Object[]) postRequest.getAllHeaders());
            logger.debug("Entity : {}", postRequest.getEntity());

            HttpResponse httpResponse = httpClient.execute(postRequest);

            // http://egloos.zum.com/flutia/v/5507510
            EntityUtils.consume(httpResponse.getEntity());

            logger.debug("LINE Platform Server Response : {}", httpResponse);
        } catch(Exception e) {
            logger.debug("LINE Platform Server Fail : {}", e);
            return false;
        }

        return true;
    }
}
