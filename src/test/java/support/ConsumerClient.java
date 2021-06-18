package support;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.io.IOException;

public class ConsumerClient {
    String url;
    public ConsumerClient(String url) {
        this.url=url;
    }

    public String sendRequest(String requestBody) throws IOException {
        HttpResponse response=
                Request.Post(url)
                .addHeader("Content","application/json")
                        .bodyString(requestBody, ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        return EntityUtils.toString(response.getEntity());


    }
}
