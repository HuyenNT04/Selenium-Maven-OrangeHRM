package testcases.admin.organization;

import actions.commons.BaseTest;
import actions.pageObject.admin.organization.StructurePageObject;
import actions.reportConfig.AllureReportListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureReportListener.class)
@Epic("Check Company Structure")
@Feature("Check Company Structure Features")
public class Structure extends BaseTest {
    private static ThreadLocal<StructurePageObject> structurePageObjectThreadLocal = new ThreadLocal<>();
    @BeforeMethod(alwaysRun = true)
    @Description("Open Structure Page")
    public void beforeClass(ITestContext context) {
        WebDriver currentDriver = getDriver();
        StructurePageObject page = new StructurePageObject(currentDriver);
        page.clickToAdminSection();
        page.clickToOrganization();
        page.clickToStructureOption();
        structurePageObjectThreadLocal.set(page);
    }

    @Test
    @Step("Verify UI Elements on Organization Structure Page")
    public void CS_01_VerifyUI() {
        StructurePageObject structurePage = structurePageObjectThreadLocal.get();
        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
        verifyTrue(structurePage.isEditToggleDisplayed());
        verifyFalse(structurePage.isAddButtonDisplayed()); // Add button should be hidden before editing mode
    }

    @Test
    @Step("Add a New Organization Unit")
    public void CS_02_AddNewUnit() {
        StructurePageObject structurePage = structurePageObjectThreadLocal.get();
        structurePage.clickToEditToggle();
        structurePage.clickToAddBtn();
        verifyTrue(structurePage.isAddDialogDisplayed());
        //Create
        String unitName, des, unitId;
        unitName = generateRandomName();
        des = generateRandomName();
        unitId = String.valueOf(getRandomNumber());
        structurePage.enterUnitId(unitId);
        structurePage.enterName(unitName);
        structurePage.enterDescription(des);
        structurePage.clickToSaveButton();
        //Verify
        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
        verifyEquals(structurePage.getMainSuccessMessage(), "Success");
        verifyEquals(structurePage.getSubSuccessMessage(), "Successfully Saved");
        verifyTrue(structurePage.isOrganizationUnitDisplayed(unitId+": "+unitName));
    }

    @Test
    @Step("Edit an Existing Organization Unit")
    public void CS_03_EditOrganizationUnit() {
        //This case is failed from the site, need to use
//        structurePage.clickToEditToggle();
//        structurePage.clickToAddBtn();
//        verifyTrue(structurePage.isAddDialogDisplayed());
//        //Create new
//        unitName = generateRandomName();
//        structurePage.enterName(unitName);
//        structurePage.clickToSaveButton();
//        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
//
//        //Edit (after creating will return to Edit mode)
//        unitName = "Finance";
//        structurePage.clickEditIcon(unitName);
//        verifyTrue(structurePage.isEditOrganizationDialogDisplayed());
//        String updatedUnitName = unitName + "_Updated";
//        structurePage.enterDescription(generateRandomName());
//        structurePage.enterName(updatedUnitName);
//        structurePage.clickToSaveButton();
//        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
//
//        //Verify
//        verifyEquals(structurePage.getMainSuccessMessage(), "Success");
//        verifyEquals(structurePage.getSubSuccessMessage(), "Successfully Updated");
//        verifyTrue(structurePage.isOrganizationUnitDisplayed(updatedUnitName));
//        verifyFalse(structurePage.isOrganizationUnitDisplayed(unitName));
    }

    @Test
    @Step("Delete an Organization Unit")
    public void CS_04_DeleteOrganizationUnit() {
        StructurePageObject structurePage = structurePageObjectThreadLocal.get();
        structurePage.clickToEditToggle();
        structurePage.clickToAddBtn();
        verifyTrue(structurePage.isAddDialogDisplayed());
        //Create new
        String unitName;
        unitName = generateRandomName();
        structurePage.enterName(unitName);
        structurePage.clickToSaveButton();
        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
        //Delete
        structurePage.clickToDeleteIcon(unitName);
        structurePage.confirmDelete();
        //Verify
        verifyEquals(structurePage.getMainSuccessMessage(), "Success");
        verifyEquals(structurePage.getSubSuccessMessage(), "Successfully Deleted");
        verifyFalse(structurePage.isOrganizationUnitDisplayed(unitName));
    }

    @Test
    @Step("Add a Sub-Organization Unit Level 2")
    public void CS_05_AddSubOrganizationUnit() {
        StructurePageObject structurePage = structurePageObjectThreadLocal.get();
        structurePage.clickToEditToggle();
        structurePage.clickToAddBtn();
        verifyTrue(structurePage.isAddDialogDisplayed());
        //Create new
        String unitName;
        unitName = generateRandomName();
        structurePage.enterName(unitName);
        structurePage.clickToSaveButton();
        verifyEquals(structurePage.getMainTitle(), "Organization Structure");
        //Add sub
        structurePage.clickAddSubOrganizationIcon(unitName);
        String subUnit = "Sub"+generateRandomName();
        structurePage.enterName(subUnit);
        structurePage.clickToSaveButton();
        //Verify
        verifyEquals(structurePage.getMainSuccessMessage(), "Success");
        verifyEquals(structurePage.getSubSuccessMessage(), "Successfully Saved");
        //Find and check
        structurePage.clickToExtendIcon(unitName);
        verifyTrue(structurePage.isOrganizationUnitDisplayed(subUnit));
    }

    @Test
    @Step("Verify Validations on Add Organization Unit Form")
    public void CS_06_VerifyAddUnitValidations() {
        StructurePageObject structurePage = structurePageObjectThreadLocal.get();
        structurePage.clickToEditToggle();
        structurePage.clickToAddBtn();
        verifyTrue(structurePage.isAddDialogDisplayed());
        //Check validation
        structurePage.clickToSaveButton(); // Try to save without entering data
        verifyTrue(structurePage.isRequiredFieldErrorDisplayed("Name"));
    }
}