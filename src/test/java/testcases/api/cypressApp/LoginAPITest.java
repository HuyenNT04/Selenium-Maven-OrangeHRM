package testcases.api.cypressApp;

import actions.api.LoginAPI;
import actions.commons.API.BaseAPITest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static actions.commons.GlobalConstants.*;

public class LoginAPITest extends BaseAPITest {
    LoginAPI loginAPI = new LoginAPI();
    @Test
    public void loginSuccessfully() {
        // Gọi hàm login
        Response response = loginAPI.login(usernameRequest, passwordRequest);
        // In toàn bộ response ra (debug)
        response.then().log().all();
        //Assertion
        //--Status code
        verifyEquals(response.getStatusCode(), 200);
        //--parameters
        String actualUsername = response.jsonPath().getString("user.username");
        verifyEquals(actualUsername, usernameRequest);
        //--cookie
        String savedCookie = loginAPI.getAuthCookie();
        System.out.println("Cookie: "+savedCookie);
        verifyNotNul(savedCookie);
    }
}
