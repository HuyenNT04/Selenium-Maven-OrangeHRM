package actions.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
//Response: đại diện cho response của API (status, body, headers...).

import static actions.commons.GlobalConstants.*;
import static io.restassured.RestAssured.given;

public class LoginAPI {
    public Response login(String username, String password){ // trả về Response type
        // Tạo body JSON động từ username và password, ddunsg theo dinh dang
        String payload = String.format("""
        {
            "username": "%s",
            "password": "%s",
            "type": "LOGIN"
        }
        """, username, password);

        //Gửi request POST với các thông tin
        Response loginResponse = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when()
                .post("/login");

        // Lưu cookie vào biến toàn cục nếu login thành công
        if (loginResponse.statusCode() == 200) {
            cookie = loginResponse.getHeader("Set-Cookie");
        }

        return loginResponse;
    }

    //Trả ra cookie đang được lưu trong biến toàn cục
    public String getAuthCookie() {
        return cookie;
    }

}
