package testsConsumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import model.UserModel;
import support.PactSupport;
import support.Utility;

import java.io.IOException;

public class SuccessFullPactTest extends PactSupport {
    @Override
    public String getRequestBody() {
        UserModel userModel=new UserModel();
        userModel.setJob("Leader");
        userModel.setName("Gautam");
        Utility utility=new Utility();
        return utility.convertToString(userModel);
    }

    @Override
    public String getResponseBody() {

        return null;
    }

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
        return super.createPact(pactDslWithProvider);
    }

    @Override
    protected String providerName() {
        return "Server";
    }

    @Override
    protected String consumerName() {
        return "User";
    }

    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext pactTestExecutionContext) throws IOException {

    }
}
