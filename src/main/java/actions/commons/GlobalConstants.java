package actions.commons;

import java.io.File;

public class GlobalConstants {
    //user
    //url/
    //folder
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php";
    public static final String URL_TEST = "http://localhost/orangehrm/web/index.php";
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String uploadFolderPath = System.getProperty("user.dir") + File.separator +"src" + File.separator + "uploadFiles" + File.separator;
    public static final String ADMIN_USERNAME = "Admin";
    public static  final String ADMIN_PASSWORD = "admin123";


    ////API
    public static final String baseUrl = "http://localhost:3001";
    public static String cookie = "";
    public static final String usernameRequest = "Jayda9";
    public static final String passwordRequest = "s3cret";


}
