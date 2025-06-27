package testcases.admin.organization;
import actions.commons.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import actions.pageObject.admin.organization.GeneralInforPageObject;
import actions.reportConfig.AllureReportListener;

@Listeners(AllureReportListener.class)
@Epic("Check General Information")
@Feature("Check demo Feature Report")
public class GeneralInformation extends BaseTest { //use all funcs in BaseTest
    //Define
    private static ThreadLocal<GeneralInforPageObject> generalInforPageThreadLocal = new ThreadLocal<>();
    String updatedName, phone, email, country;

    @BeforeMethod(alwaysRun = true)
    @Description("Open Generate Information Page")
    public void beforeClass(ITestContext context){
        WebDriver currentDriver = getDriver();
        GeneralInforPageObject page = new GeneralInforPageObject(currentDriver);
        page.clickToAdminSection();
        page.clickToOrganization();
        page.clickToGenerateInformationOption();
        generalInforPageThreadLocal.set(page);
    }
    @Test
    @Step("Check UI")//mandtory for report
    public void GI_01_CheckUI(){
        GeneralInforPageObject generalInforPage = generalInforPageThreadLocal.get();
        verifyTrue(generalInforPage.checkTitleDisplayed());
        verifyTrue(generalInforPage.checkEditToggleDisplayed());
        verifyTrue(generalInforPage.checkNumberOfEmployeeDisplayed());
    }
    @Test
    @Step("Edit General Information")
    @Severity(SeverityLevel.TRIVIAL)
    public void GI_01_EditGeneralInformation() {
        GeneralInforPageObject generalInforPage = generalInforPageThreadLocal.get();
        //data
        updatedName =  "Huyen Checked"+getRandomNumber();
        phone = "0934653"+getRandomNumber();
        email = "check"+getRandomNumber()+"@gmail.com";
        country = "Antigua and Barbuda";
        //Verify
        generalInforPage.clickToEditToggle();
        generalInforPage.enterOrganizationNameTextbox(updatedName);
        generalInforPage.enterPhoneTextbox(phone);
        generalInforPage.enterEmailTextbox(email);
        generalInforPage.changeCountryDropdown(country);
        generalInforPage.clickTosaveEditedData();
        verifyEquals(generalInforPage.getSuccessMessage(),"Successfully Updated");
        verifyEquals(generalInforPage.getUpdatedOrganizationName(),updatedName);
        verifyEquals(generalInforPage.getUpdatedPhone(),phone);
        verifyEquals(generalInforPage.getUpdatedEmail(),email);
        verifyEquals(generalInforPage.getUpdatedCountry(),country);
    }
}
