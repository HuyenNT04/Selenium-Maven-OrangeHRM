package actions.api;

import actions.commons.API.BaseAPIRequest;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static actions.commons.GlobalConstants.baseUrl;
import static io.restassured.RestAssured.given;

public class NotificationAPI extends BaseAPIRequest {
    private String authCookie;
    //Constructor
    public NotificationAPI(String cookie){
        this.authCookie = cookie;
    }
    //Function
    public Response getAllNotification(String accept, String Cookie, String endpoint){
        //GET method does not send payload --> don't have payload
        //Send GET request and receive response
        Response response = given()
                .baseUri(baseUrl)
                .accept(accept)
                .header("Cookie", Cookie) // SEND cookie
                .when()
                .get(endpoint);
        return response;
    }
    public int getResponseStatusCode(Response response){
        return getResponseSttCode(response);
    }

    public long getResponseTime(Response response){
        long responseTime = response.time(); //miliseconds
        System.out.println("⏱️ Response time: " + responseTime + "ms");
        return responseTime;
    }

    public boolean validatedReqiredKeysExist(Response response,String listName, List<String> requiredKeys){
        try {
            List<Map<String, Object>> results = response.jsonPath().getList(listName);
            validateKeysExist(results, requiredKeys);
            return true;
        }catch (Exception e) {
            System.err.println("Key validation failed: " + e.getMessage());
            return false;
        }
    }

    //Check returned type is correct
    public boolean isKeyOfExpectedType(Response response, String listName, String key, Class<?> expectedType) {//Class<?>: kiểu class generic, sure truyen Class, ?: any type
        //String.class,Boolean.class,Integer.class...
        List<Map<String, Object>> results = response.jsonPath().getList(listName);
        for (Map<String, Object> item : results) {
            Object value = item.get(key);
            if (value != null && !expectedType.isInstance(value)) {
                return false;
            }
        }
        return true;
    }
    public boolean isKeyOfFixedList(Response response, String listName, String key, List<String> fixedValue){
        List<Map<String, Object>> results = response.jsonPath().getList(listName);
        for (Map<String, Object> item : results) {
            String value = (String) item.get(key);
            if (value != null && !fixedValue.contains(value)){
                return false;
            }
        }
        return true;
    }
}
