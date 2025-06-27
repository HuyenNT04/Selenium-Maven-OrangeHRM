package actions.commons.API;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPIRequest {
    //common logic for using API Testing
    public static RequestSpecification initRequest(String token, String baseUrl) {
        return given()
                .baseUri(baseUrl) //URL goc cho API Request
                .cookie("orangehrm=", token)
                .header("accept","application/json")
                .contentType("application/json");
    }
    public void validateKeysExist(List<Map<String, Object>> jsonList, List<String> keys) {
        for (Map<String, Object> item : jsonList) {
            for (String key : keys) {
                Assert.assertTrue(item.containsKey(key), "Missing key: " + key + " in object: " + item);
            }
        }
    }
    public int getResponseSttCode(Response response){
        return response.getStatusCode();
    }
    public String getResponseBody(Response response){
        return response.getBody().asString();
    }
}
