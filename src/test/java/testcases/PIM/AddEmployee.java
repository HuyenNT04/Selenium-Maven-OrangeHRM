package testcases.PIM;

import actions.commons.BaseTest;
import actions.commons.DataProviderFactory;
import actions.pageObject.PIM.AddEmployeePageObject;
import actions.pageObject.PIM.EmployeeListPageObject;
import actions.pageObject.myInfo.PersonalDetailPageObject;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static actions.commons.GlobalConstants.uploadFolderPath;

public class AddEmployee extends BaseTest {
    private static ThreadLocal<AddEmployeePageObject> addEmployeePageObjectThreadLocal = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Description("Open Add Employee Page")
    public void beforeClass(ITestResult result, ITestContext context) {
        WebDriver currentDriver = getDriver();
        AddEmployeePageObject page = new AddEmployeePageObject(currentDriver);
        page.clickPIMSection();
        page.openAddEmployeePage(currentDriver);
        addEmployeePageObjectThreadLocal.set(page);//Gán (set) đối tượng page vào ThreadLocal biến addEmployeePageObjectThreadLocal
    }
    @Test
    @Step("AE_01_CheckUI")
    public void AE_01_CheckUI() {
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        verifyTrue(addEmployeePage.isAddEmployeePageDisplayed());
        verifyTrue(addEmployeePage.isDefaultAvatarDisplayed());
    }
    @Test
    @Step("AE_02_Add_Employee")
    public void AE_02_Add_Employee_Without_Login_Detail() {
        //data
        String firstName, middleName, lastName, employeeId, filePath;
        firstName = generateRandomName()+ " check FirstName";
        middleName = generateRandomName()+ " check MiddleName";
        lastName = generateRandomName()+ "check LastName";
        employeeId = generateRandomName();
        String specFileName = "8888.png";
        filePath = uploadFolderPath + specFileName;
        //Add
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeID(employeeId);
        addEmployeePage.uploadProfilePicture(filePath);
        addEmployeePage.clickSaveButton();
        //Verify
        verifyTrue(isSuccessToastDisplayed(getDriver()));
        verifyEquals(getMainSuccessMessage(getDriver()),"Success");
        verifyEquals(getSubSuccessMessage(getDriver()),"Successfully Saved");
        //Check flow
        //check show Personal Detail page
        PersonalDetailPageObject personalDetailPage = addEmployeePage.PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPage.getMainTitle(), "Personal Details");
        System.out.println(personalDetailPage.getMainTitle());
        System.out.println(personalDetailPage.getFirstName());
        verifyEquals(personalDetailPage.getFirstName(), firstName);
        verifyEquals(personalDetailPage.getMiddleName(), middleName);
        verifyEquals(personalDetailPage.getLastName(), lastName);
        verifyEquals(personalDetailPage.getEmployeeId(), employeeId);
    }
    @Test
    @Step("AE_03_Add_Employee")
    public void AE_03_Add_Employee_With_Login_Detail() {
        //data
        String firstName, middleName, lastName, employeeId, userName, password;
        firstName = generateRandomName()+ " check FirstName";
        middleName = generateRandomName()+ " check MiddleName";
        lastName = generateRandomName()+ "check LastName";
        employeeId = generateRandomName();
        userName = generateRandomName() + "userName";
        password = generateRandomName() + getRandomNumber();
        String specFileName = "8888.png";
        String filePath = uploadFolderPath + specFileName;
        //Add
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeID(employeeId);
        addEmployeePage.uploadProfilePicture(filePath);
        addEmployeePage.clickCreateLoginDetailsToggle();
        addEmployeePage.enterUsername(userName);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(password);
        addEmployeePage.clickSaveButton();
        //Verify
        verifyTrue(isSuccessToastDisplayed(getDriver()));
        verifyEquals(getMainSuccessMessage(getDriver()),"Success");
        verifyEquals(getSubSuccessMessage(getDriver()),"Successfully Saved");
        //Check displaying
        PersonalDetailPageObject personalDetailPage = addEmployeePage.PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPage.getMainTitle(), "Personal Details");
        verifyEquals(personalDetailPage.getFirstName(), firstName);
        verifyEquals(personalDetailPage.getMiddleName(), middleName);
        verifyEquals(personalDetailPage.getLastName(), lastName);
        verifyEquals(personalDetailPage.getEmployeeId(), employeeId);
    }
    @Test(dataProvider = "Validate single input field", dataProviderClass = DataProviderFactory.class)
    @Step("AE_04_Check_Validation_Leave_Blank_Employee_Infor")
    public void AE_04_Check_Validation_Employee_Infor(String firstName, String lastName, String userName, String password, String label, String error) {
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.clickCreateLoginDetailsToggle();
        addEmployeePage.enterUsername(userName);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(password);
        addEmployeePage.clickSaveButton();
        //Verify
        verifyEquals(addEmployeePage.getEmployeeInforError(label), error);
    }
    @Test(dataProvider = "Validate single input field", dataProviderClass = DataProviderFactory.class)
    @Step("AE_05_Check_Validation_Leave_Blank_Login_Details")
    public void AE_05_Check_Validation_Login_Details(String firstName, String lastName, String userName, String password, String label, String error) {
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.clickCreateLoginDetailsToggle();
        addEmployeePage.enterUsername(userName);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(password);
        addEmployeePage.clickSaveButton();
        //Verify
        verifyEquals(addEmployeePage.getEmployeeLoginDetails(label), error);
    }
    @Test
    @Step("AE_06_Check_Flow_Added Employee-Employee List-Detail")
    public void AE_06_Check_Added_Employee_is_displaying_in_Employee_List(){
        //data
        String firstName, middleName, lastName, employeeId;
        firstName = generateRandomName()+ " check FirstName";
        middleName = generateRandomName()+ " check MiddleName";
        lastName = generateRandomName()+ "check LastName";
        employeeId = String.valueOf(getRandomNumber()); //ep kieu tuong minh
        //Add new
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeID(employeeId);
        addEmployeePage.clickSaveButton();
        //Detail page is displaying
        PersonalDetailPageObject personalDetailPage = PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPage.getMainTitle(), "Personal Details");

        //Check displaying in Listing page
        EmployeeListPageObject employeeListPage = openEmployeeListPage(getDriver());
        employeeListPage.enterEmployeeId(employeeId);
        employeeListPage.clickToSearchBtn();
        verifyTrue(employeeListPage.isEmployeeDisplay(employeeId, firstName, middleName, lastName));
        employeeListPage.clickToEditIcon(employeeId);

        //Check right information from Detail page
        PersonalDetailPageObject personalDetailPageEdit = PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPageEdit.getMainTitle(), "Personal Details");
        verifyEquals(personalDetailPageEdit.getFirstName(), firstName);
        verifyEquals(personalDetailPageEdit.getMiddleName(), middleName);
        verifyEquals(personalDetailPageEdit.getLastName(), lastName);
        verifyEquals(personalDetailPageEdit.getEmployeeId(), employeeId);
    }
    @Test
    @Step("AE_07_Check_Updated_in_List will reflect to detail page")
    public void AE_07_Check_Updated_in_List_will_reflect_to_detail_page(){
        //Prepare data
        String firstName = generateRandomName() + " check FirstName";
        String middleName = generateRandomName() + " check MiddleName";
        String lastName = generateRandomName() + "check LastName";
        String employeeId = "Emp_" + UUID.randomUUID().toString().substring(0, 3); //unique ID

        String updatedFirstName = firstName + "edit";
        String updatedMiddleName = middleName + "edit";
        String updatedLastName = lastName + "edit";
        String updatedEmployeeId = employeeId + "123";

        //Add new
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeID(employeeId);
        addEmployeePage.clickSaveButton();
        ///Detail page is displaying
        PersonalDetailPageObject personalDetailPage = PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPage.getMainTitle(), "Personal Details");

        //Update data from Listing page
        EmployeeListPageObject employeeListPage = openEmployeeListPage(getDriver());
        employeeListPage.enterEmployeeId(employeeId);
        employeeListPage.clickToSearchBtn();
        verifyTrue(employeeListPage.isEmployeeDisplay(employeeId, firstName, middleName, lastName));
        employeeListPage.clickToEditIcon(employeeId);

        PersonalDetailPageObject personalDetailPageVerify = PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPageVerify.getMainTitle(), "Personal Details");
        personalDetailPageVerify.editFirstName(updatedFirstName);
        personalDetailPageVerify.editMiddleName(updatedMiddleName);
        personalDetailPageVerify.editLastName(updatedLastName);
        personalDetailPageVerify.editEmployeeId(updatedEmployeeId);
        personalDetailPageVerify.clickToSaveButton();
        verifyEquals(getSubSuccessMessage(getDriver()),"Successfully Updated");

        //Verify in detail page
        verifyEquals(personalDetailPage.getFirstName(), updatedFirstName);
        verifyEquals(personalDetailPage.getMiddleName(), updatedMiddleName);
        verifyEquals(personalDetailPage.getLastName(), updatedLastName);
        verifyEquals(personalDetailPage.getEmployeeId(), updatedEmployeeId);

        //Verify in Listing
        EmployeeListPageObject employeeListPageVerify = openEmployeeListPage(getDriver());
        ///Check displaying editted data
        employeeListPageVerify.enterEmployeeId(updatedEmployeeId);
        employeeListPageVerify.clickToSearchBtn();
        verifyTrue(employeeListPageVerify.isEmployeeDisplay(updatedEmployeeId, updatedFirstName, updatedMiddleName, updatedLastName));
        ///Check disappearing old data
        employeeListPageVerify.enterEmployeeId(employeeId);
        employeeListPageVerify.clickToSearchBtn();
        verifyFalse(employeeListPageVerify.isEmployeeDisplay(employeeId, firstName, middleName, lastName));
    }
    @Test(groups = "runnow")
    @Step("AE_08_Check_Updated_in_Detail_page_reflect_to_listing_page")
    public void AE_08_Check_Updated_in_Detail_page_reflect_to_listing_page(){
        //Prepare data
        String firstName, middleName, lastName, employeeId;
        firstName = generateRandomName()+ " check FirstName";
        middleName = generateRandomName()+ " check MiddleName";
        lastName = generateRandomName()+ "check LastName";
        employeeId = String.valueOf(getRandomNumber());

        String updatedFirstName = firstName + "edit";
        String updatedMiddleName = middleName + "edit";
        String updatedLastName = lastName + "edit";
        String updatedEmployeeId = employeeId + "123";

        //Add new
        AddEmployeePageObject addEmployeePage = addEmployeePageObjectThreadLocal.get();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeID(employeeId);
        addEmployeePage.clickSaveButton();
        ///Detail page is displaying
        PersonalDetailPageObject personalDetailPage = PersonalDetailPageDisplaying(getDriver());
        verifyEquals(personalDetailPage.getMainTitle(), "Personal Details");

        //Update data directly from detail page
        personalDetailPage.editFirstName(updatedFirstName);
        personalDetailPage.editMiddleName(updatedMiddleName);
        personalDetailPage.editLastName(updatedLastName);
        personalDetailPage.editEmployeeId(updatedEmployeeId);
        personalDetailPage.clickToSaveButton();
        verifyEquals(getSubSuccessMessage(getDriver()),"Successfully Updated");

        //Verify in detail page
        verifyEquals(personalDetailPage.getFirstName(), updatedFirstName);
        verifyEquals(personalDetailPage.getMiddleName(), updatedMiddleName);
        verifyEquals(personalDetailPage.getLastName(), updatedLastName);
        verifyEquals(personalDetailPage.getEmployeeId(), updatedEmployeeId);

        //Verify in Listing
        EmployeeListPageObject employeeListPage = openEmployeeListPage(getDriver());
        ///Check displaying editted data
        employeeListPage.enterEmployeeId(updatedEmployeeId);
        employeeListPage.clickToSearchBtn();
        verifyTrue(employeeListPage.isEmployeeDisplay(updatedEmployeeId, updatedFirstName, updatedMiddleName, updatedLastName));
        ///Check disappearing old data
        employeeListPage.enterEmployeeId(employeeId);
        employeeListPage.clickToSearchBtn();
        verifyFalse(employeeListPage.isEmployeeDisplay(employeeId, firstName, middleName, lastName));
    }
}
