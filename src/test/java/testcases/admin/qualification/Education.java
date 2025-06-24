package testcases.admin.qualification;

import actions.commons.BaseTest;
import actions.pageObject.admin.qualification.EducationPageObject;
import actions.reportConfig.AllureReportListener;
import actions.utilities.DBUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureReportListener.class)
@Epic("Check Quality - Education")
@Feature("Check demo Feature Education - Database")
public class Education extends BaseTest {
    WebDriver driver;
    EducationPageObject educationPage;
    String name, updatedName, name2;
    @BeforeClass(alwaysRun = true)
    @Description("Open Admin Page")
    public void beforeClass(ITestContext context) {
        //driver = (WebDriver) context.getAttribute("WebDriver"); // get driver from Context
        driver = getDriver();
        educationPage = new EducationPageObject(driver, dbUtils);
        educationPage.clickToAdminSection();
    }
    @BeforeMethod(alwaysRun = true)
    @Description("For independent Testcases")
    public void beforeTestcases(){
        educationPage.clickToQualification();
        educationPage.clickToEducationOption();
    }
    @Test
    @Step("ED_01_CheckUI")
    public void ED_01_CheckUI() {
        verifyTrue(educationPage.isMainTitleDisplayed());
        verifyTrue(educationPage.isAddButtonDisplayed());
    }
    @Test
    @Step("ED_02_User_Create_New_Education")
    public void ED_02_User_Create_New_Education() {
        name = generateRandomName();
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name);
        educationPage.clickToSaveButton();

        //Verify
        verifyEquals(educationPage.getMainSuccessMessage(),"Success");
        verifyEquals(educationPage.getSubSuccessMessage(),"Successfully Saved");
        verifyTrue(educationPage.isMainTitleDisplayed());
        verifyTrue(educationPage.isEducationDisplayed(name));

        //Verify Database
        verifyTrue(educationPage.isEducationExist(name));

        //Clear Data from DB
        educationPage.deleteEducationByName(name);
    }
    @Test
    @Step("ED_03_User_Update_Education")
    public void ED_03_User_Update_Education() {
        name = generateRandomName();
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name);
        educationPage.clickToSaveButton();
        verifyTrue(educationPage.isMainTitleDisplayed());

        //Edit
        educationPage.clickToEditIcon(name);
        verifyTrue(educationPage.isEditLableDisplayed());
        updatedName = name + "updated";
        educationPage.changeName(updatedName);
        educationPage.clickToSaveButton();

        //Verify from UI
        verifyEquals(educationPage.getMainSuccessMessage(),"Success");
        verifyEquals(educationPage.getSubSuccessMessage(),"Successfully Updated");
        verifyTrue(educationPage.isMainTitleDisplayed());
        verifyTrue(educationPage.isEducationDisplayed(updatedName));
        verifyFalse(educationPage.isEducationDisplayed(name));

        //Verify Database
        verifyTrue(educationPage.isEducationExist(updatedName));
        verifyFalse(educationPage.isEducationExist(name));

        //Clear Data from DB
        educationPage.deleteEducationByName(updatedName);
    }
    @Test
    @Step("ED_04_User_Delete_Education")
    public void ED_04_User_Delete_Education() {
        name = generateRandomName();
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name);
        educationPage.clickToSaveButton();
        verifyTrue(educationPage.isMainTitleDisplayed());

        //Edit
        educationPage.clickToDeleteIcon(name);
        verifyTrue(educationPage.isDeleteDialogDisplayed());
        educationPage.clickToYesButton();

        //Verify from UI
        verifyEquals(educationPage.getMainSuccessMessage(),"Success");
        verifyEquals(educationPage.getSubSuccessMessage(),"Successfully Deleted");
        verifyTrue(educationPage.isMainTitleDisplayed());
        verifyFalse(educationPage.isEducationDisplayed(name));

        //Verify Database
        verifyFalse(educationPage.isEducationExist(name));

        //Clear Data from DB
        educationPage.deleteEducationByName(name);

    }
    @Test(groups = "runnow")
    @Step("ED_04_User_Delete_Multi_Education")
    public void ED_05_User_Delete_Multi_Education() {
        //create Data test
        name = generateRandomName();
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name);
        educationPage.clickToSaveButton();
        verifyTrue(educationPage.isMainTitleDisplayed());

        name2 = generateRandomName();
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name2);
        educationPage.clickToSaveButton();
        verifyTrue(educationPage.isMainTitleDisplayed());

        //Check on Checkboxes and delete
        educationPage.checkToCheckBox(name);
        educationPage.checkToCheckBox(name2);
        educationPage.clickToDeleteBtn();
        verifyTrue(educationPage.isDeleteDialogDisplayed());
        educationPage.clickToYesButton();

        //Verify from UI
        verifyEquals(educationPage.getMainSuccessMessage(),"Success");
        verifyEquals(educationPage.getSubSuccessMessage(),"Successfully Deleted");
        verifyTrue(educationPage.isMainTitleDisplayed());
        verifyFalse(educationPage.isEducationDisplayed(name));
        verifyFalse(educationPage.isEducationDisplayed(name2));

        //Verify from Database
        verifyFalse(educationPage.isEducationExist(name));
        verifyFalse(educationPage.isEducationExist(name2));

        //Clear data from DB
        educationPage.deleteEducationByName(name);
        educationPage.deleteEducationByName(name2);
    }
    @Test
    public void ED_06_User_Can_Not_Add_Duplicate_Education(){
        //create data 1 from INSERT in DB
        name = generateRandomName();
        educationPage.updateEducation(name);
        //Add new duplicated data from UI
        educationPage.clickToAddBtn();
        verifyTrue(educationPage.isAddEducationLabelDisplayed());
        educationPage.enterName(name);
        educationPage.clickToSaveButton();

        //Verify from UI
        Assert.assertEquals(educationPage.getErrorUnderInputField(), "Already exists");
        //Verify from DB
        Assert.assertEquals(educationPage.countEducationByName(name), 1);
        //Clear data from DB
        educationPage.deleteEducationByName(name);
    }
}
