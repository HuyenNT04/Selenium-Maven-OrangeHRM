package actions.commons;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderFactory extends BaseTest{
    @DataProvider(name = "Validate input 1 Language", parallel = false)
    public Object[][] languageData(){
        return new Object[][]{
                {"Check Only Text" + generateRandomName()},
                {"Check Only Number"+ generateRandomName()},
                {"@#$%^&*(^*&"+ generateRandomName()}
        };
    }
    @DataProvider(name = "Validate input 1 Language - SwitchCase", parallel = false)
    public Object[][] languageDataForSwitchCase(Method m){
        switch (m.getName()){
            case "LG_03a_CheckValidation_AddNew_SwitchCase":
                return new Object[][]{
                        {"Check Only Text"+ generateRandomName()},
                        {"312424245363"+ generateRandomName()},
                        {"@#$%^&*(^*&"+ generateRandomName()}
                };
            case "LG_03b_CheckValidation_AddNew_LeaveBlank_SwitchCase":
                return new Object[][]{
                        {""}
                };
        }
        return null;
    }
    @DataProvider(name = "Validate single input field")
    public Object[][] addEmployeeCheckErrorSingleField(Method m){
        switch (m.getName()){
            case "AE_04_Check_Validation_Employee_Infor":
                return new Object[][]{
                        {"","last"+generateRandomName(), "username", "Dnhh1245@@", "firstName", "Required"},
                        {"first"+generateRandomName(), "", "username", "Dnhh1245@@", "lastName", "Required"}
                };

            case "AE_05_Check_Validation_Login_Details":
                return new Object[][]{
                        {"first"+generateRandomName(), "last"+generateRandomName(), "", "Dnhh1245@@", "Username", "Required"},
                        {"first"+generateRandomName(), "last"+generateRandomName(), "username", "", "Password", "Required"},
                        {"first"+generateRandomName(), "last"+generateRandomName(), "username", "", "Confirm Password", "Passwords do not match"},
                };
        }
        return null;
    }
}
