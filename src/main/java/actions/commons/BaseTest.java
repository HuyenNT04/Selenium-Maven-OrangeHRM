package actions.commons;

//import actions.utilities.DBConnection;
//import actions.utilities.DBUtils;
//import io.restassured.response.Response;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.Assert;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static actions.commons.GlobalConstants.*;
import static io.restassured.RestAssured.given;

public class BaseTest extends BasePage{
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>(); //for using separated context in every test
    protected WebDriver getDriver(){
        return driverThreadLocal.get();
    }
//    protected Connection connection;
//    protected DBUtils dbUtils;

    //----------------------Annotations for Testcases----------------------------

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless", "isCICD"})
    public void setUp(String browserName, @Optional("false") boolean headless,@Optional("false") String isCICD, ITestContext context) throws SQLException {
//        try {
//            connection = DBConnection.getConnection();
//            dbUtils = new DBUtils(connection); // Khởi tạo DBUtils với kết nối hiện tại
            boolean isCI = Boolean.parseBoolean(isCICD); //do o file xml gia tri truyen vao dang la string, nen phai parse qua Boolean
            WebDriver driver = openBrowser(browserName, headless, isCI);
            driverThreadLocal.set(driver);
            context.setAttribute("WebDriver", driver); // save Driver into Context to report
//        } catch (SQLException e) {
//            System.err.println("Failed to establish database connection: " + e.getMessage());
//            throw new RuntimeException("Failed to setup test environment due to DB connection error", e);
//        }
    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
//        DBConnection.closeConnection(connection);
    }
    public WebDriver openBrowser (String browserName, boolean headless, boolean isCICD) {
        WebDriver driver;
        if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");// run in Headless mode
            }
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--ignore-certificate-errors");
            // CICD-specific flags
            if (isCICD) { //Running in CI/CD mode
                options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis()); //user-data-dir only use for CICD, not parallel local
                options.addArguments("--disable-gpu");// optional, nhất là trong Docker
                options.addArguments("--no-sandbox");// optional nếu chạy CI/CD
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            driver = new EdgeDriver(options);
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.edgedriver().clearDriverCache().setup();
            FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                driver = new FirefoxDriver(options);
        } else {
            throw new RuntimeException("Browser name is not valid!!!");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       // Login
        openPageURL(driver, GlobalConstants.URL);
        waitForElementVisible(driver,"//input[@name='username']");
        sendkeyToElement(driver,"//input[@name='username']",ADMIN_USERNAME);
        waitForElementVisible(driver,"//input[@name='password']");
        sendkeyToElement(driver,"//input[@name='password']",ADMIN_PASSWORD);
        clickToElement(driver,"//button[@type='submit']");

//        //For API Testing
//        Response response = given()
//                .baseUri(baseUrl) //URL goc cho API Request
//                .header("accept","application/json")
//                .contentType("application/json")
//                .when()
//                .get("/web/index.php/api/v2/dashboard/employees/locations");
//        String sessionCookie  = response.getCookie("orangehrm");
//        System.out.println("Cookie = " + sessionCookie );
//        cookie  = sessionCookie;

          //For testing DataBase
//        openPageURL(driver, GlobalConstants.URL_TEST);
//        waitForElementVisible(driver,"//input[@name='username']");
//        sendkeyToElement(driver,"//input[@name='username']","huyenhuyen");
//        waitForElementVisible(driver,"//input[@name='password']");
//        sendkeyToElement(driver,"//input[@name='password']","neTGv4Zj9!hlesdg@1");
//        clickToElement(driver,"//button[@type='submit']");

        return driver;
    }

//----------------------Common Functions for all Testcases----------------------------
    public void verifyTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public void verifyFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    public void verifyEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    public int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10000);  // random numbers 0 - 999
    }

    public String getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }
    public String generateRandomName(){
        return "check" + getRandomNumber();
    }
}
