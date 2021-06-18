package support;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class PactSupport extends ConsumerPactTest {
  static final String requestBody =
      "{\n"
          + "    \"url\": \"https://reqres.in/#support-heading\",\n"
          + "    \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n"
          + "  }";
  static final String responseBody = "{\n" + "    \"Success\": \"Success appreciated!\"\n" + "  }";

  @Override
  protected RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
    return pactDslWithProvider
        .given("This is the first test")
        .uponReceiving("This is a short description")
        .body(requestBody)
        .method("POST")
        .headers(getHeaders())
        .path("/this/path")
        .willRespondWith()
        .status(201)
        .body(responseBody)
        .toPact();
  }

  private Map<String, String> getHeaders() {
    Map<String, String> map = new HashMap<>();
    map.put("Content", "application/json");
    return map;
  }

  @Override
  protected String providerName() {
    return null;
  }

  @Override
  protected String consumerName() {
    return null;
  }

  @Override
  protected void runTest(MockServer mockServer, PactTestExecutionContext pactTestExecutionContext)
      throws IOException {

    ConsumerClient consumerClient = new ConsumerClient(mockServer.getUrl() + "/this/path");
    String response = consumerClient.sendRequest(requestBody);
    JSONAssert.assertEquals(response, responseBody, JSONCompareMode.LENIENT);
  }

  public abstract String getRequestBody();

  public abstract String getResponseBody();
}
