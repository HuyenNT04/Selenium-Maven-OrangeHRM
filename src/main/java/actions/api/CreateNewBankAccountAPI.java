package actions.api;

import io.restassured.response.Response;

import static actions.commons.GlobalConstants.baseUrl;
import static io.restassured.RestAssured.*;

public class CreateNewBankAccountAPI {
    private String authCookie;
    //Constructor
    public CreateNewBankAccountAPI(String cookie) {
        this.authCookie = cookie;
    }

    public Response createNewBankAcc(String bankName,String accountNumber,String routingNumber) {
        //Dynamic request body to send
        String payload = String.format("""
                    {
                      "operationName": "CreateBankAccount",
                      "query": "mutation CreateBankAccount($bankName: String!, $accountNumber: String!, $routingNumber: String!) {\\n  createBankAccount(\\n    bankName: $bankName\\n    accountNumber: $accountNumber\\n    routingNumber: $routingNumber\\n  ) {\\n    id\\n    uuid\\n    userId\\n    bankName\\n    accountNumber\\n    routingNumber\\n    isDeleted\\n    createdAt\\n  }\\n}",
                      "variables": {
                        "userId": "qgunlzx_0",
                        "bankName": "%s",
                        "accountNumber": "%s",
                        "routingNumber": "%s"
                      }
                    }
                """, bankName, accountNumber, routingNumber);
        //Send POST request and receive Response
        Response response = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", authCookie) // SEND cookie
                .body(payload)
                .when()
                .post("/graphql");
        return response;
    }
}
