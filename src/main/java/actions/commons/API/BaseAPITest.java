package actions.commons.API;

import actions.api.LoginAPI;
import actions.commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

import static actions.commons.GlobalConstants.*;


public class BaseAPITest {
    LoginAPI loginAPI = new LoginAPI();
    @BeforeTest(alwaysRun = true)
    public void setUpSession() {
        //Login and get session cookie
        loginAPI.login(usernameRequest, passwordRequest);
        cookie = loginAPI.getAuthCookie();
    }
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        System.out.println("🧹 Cleaning up session...");
        cookie = null;
    }

    //-----------------------------------
    public void verifyTrue(boolean condition) {
        Assert.assertTrue(condition);
    }
    public void verifyFalse(boolean condition) {
        Assert.assertFalse(condition);
    }
    public void verifyEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }
    public void verifyNotNul(Object condition){
        Assert.assertNotNull(condition);
    }
    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(10000);  // random numbers 0 - 999
    }
}
