package actions.pageObject.PIM;

import actions.commons.BasePage;
import interfaces.pageUIs.BasePageUI;
import interfaces.pageUIs.PIM.AddEmployeePageUI;
import org.openqa.selenium.WebDriver;

public class AddEmployeePageObject extends BasePage{
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
    }
    // --- Navigation ---
    public void clickPIMSection() {
        waitForElementClickable(driver, AddEmployeePageUI.PIM_SECTION);
        clickToElement(driver, AddEmployeePageUI.PIM_SECTION);
    }

    public void clickAddEmployeeTab() {
        waitForElementClickable(driver, AddEmployeePageUI.ADD_EMPLOYEE_TAB);
        clickToElement(driver, AddEmployeePageUI.ADD_EMPLOYEE_TAB);
    }
    // --- Input Actions ---
    public void enterFirstName(String firstName) {
        waitForElementVisible(driver, AddEmployeePageUI.FIRST_NAME_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_INPUT, firstName);
    }

    public void enterMiddleName(String middleName) {
        waitForElementVisible(driver, AddEmployeePageUI.MIDDLE_NAME_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.MIDDLE_NAME_INPUT, middleName);
    }

    public void enterLastName(String lastName) {
        waitForElementVisible(driver, AddEmployeePageUI.LAST_NAME_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_INPUT, lastName);
    }
    public void enterEmployeeID(String employeeId) {

        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.EMPLOYEE_ID_INPUT, employeeId);
    }

    public String getEmployeeId() {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_INPUT);
        // Use getAttribute("value") for input fields
        return getElementAttribute(driver, AddEmployeePageUI.EMPLOYEE_ID_INPUT, "value");
    }

    public void uploadProfilePicture(String filePath) {
        // Use the specific method from BasePage to handle hidden input uploads
        sendkeyToUpload(driver, AddEmployeePageUI.IMPORT_IMAGE_INPUT, filePath);
        // Add a small wait if needed for the preview to update
        sleepInSeconds(2); // Example, explicit wait for image src change is better
    }

    public void clickCreateLoginDetailsToggle() {
        waitForElementClickable(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS_TOGGLE);
        clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS_TOGGLE);
    }

    public void enterUsername(String username) {
        waitForElementVisible(driver, AddEmployeePageUI.USERNAME_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        waitForElementVisible(driver, AddEmployeePageUI.PASSWORD_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.PASSWORD_INPUT, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        waitForElementClickable(driver, AddEmployeePageUI.CONFIRM_PASSWORD_INPUT);
        sendkeyToElement(driver, AddEmployeePageUI.CONFIRM_PASSWORD_INPUT, confirmPassword);
    }

    public void clickSaveButton() {
        waitForElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        // Wait for potential loading spinner to disappear if applicable
        //waitForLoadingIconToDisappear(); // Assuming BasePage has this
    }

    public void clickCancelButton() {
        waitForElementClickable(driver, AddEmployeePageUI.CANCEL_BUTTON);
        clickToElement(driver, AddEmployeePageUI.CANCEL_BUTTON);
    }
    // --- Verification/Getter Methods ---
    public boolean isAddEmployeePageDisplayed() {
        waitForElementVisible(driver, AddEmployeePageUI.MAIN_TITLE);
        return isElementDisplayed(driver, AddEmployeePageUI.MAIN_TITLE);
    }

    public boolean isDefaultAvatarDisplayed() {
        waitForElementVisible(driver,AddEmployeePageUI.AVATAR_IMAGE);
        String currentSrc = getElementAttribute(driver, AddEmployeePageUI.AVATAR_IMAGE, "src");
        return currentSrc != null && currentSrc.contains(AddEmployeePageUI.DEFAULT_AVATAR_IMAGE_SRC_PART);
    }

    public boolean isAvatarUpdated(String defaultSrcPart) {
        waitForElementVisible(driver, AddEmployeePageUI.AVATAR_IMAGE);
        String currentSrc = getElementAttribute(driver, AddEmployeePageUI.AVATAR_IMAGE, "src");
        // Check if the src is NOT null and does NOT contain the default part anymore
        return currentSrc != null && !currentSrc.contains(defaultSrcPart);
    }

    public String getFirstNameRequiredError() {
        waitForElementVisible(driver, AddEmployeePageUI.FIRST_NAME_REQUIRED_ERROR);
        return getElementText(driver, AddEmployeePageUI.FIRST_NAME_REQUIRED_ERROR);
    }
    public String getEmployeeInforError(String text){
        waitForElementVisible(driver, getDynamicLocator(AddEmployeePageUI.DYNAMIC_ERROR_EMPLOYEE_INFOR, text));
        return getElementText(driver, getDynamicLocator(AddEmployeePageUI.DYNAMIC_ERROR_EMPLOYEE_INFOR, text));
    }
    public String getEmployeeLoginDetails(String text){
        waitForElementVisible(driver, getDynamicLocator(AddEmployeePageUI.DYNAMIC_ERROR_LOGIN_DETAILS, text));
        return getElementText(driver, getDynamicLocator(AddEmployeePageUI.DYNAMIC_ERROR_LOGIN_DETAILS, text));
    }
    public String getLastNameRequiredError() {
        waitForElementVisible(driver, AddEmployeePageUI.LAST_NAME_REQUIRED_ERROR);
        return getElementText(driver, AddEmployeePageUI.LAST_NAME_REQUIRED_ERROR);
    }

    public String getUsernameRequiredError() {
        waitForElementVisible(driver, AddEmployeePageUI.USERNAME_REQUIRED_ERROR);
        return getElementText(driver, AddEmployeePageUI.USERNAME_REQUIRED_ERROR);
    }

    public String getPasswordRequiredError() {
        waitForElementVisible(driver, AddEmployeePageUI.PASSWORD_REQUIRED_ERROR);
        return getElementText(driver, AddEmployeePageUI.PASSWORD_REQUIRED_ERROR);
    }

    public String getConfirmPasswordRequiredError() {
        waitForElementVisible(driver, AddEmployeePageUI.CONFIRM_PASSWORD_REQUIRED_ERROR);
        return getElementText(driver, AddEmployeePageUI.CONFIRM_PASSWORD_REQUIRED_ERROR);
    }

    public String getPasswordMismatchError() {
        waitForElementVisible(driver, AddEmployeePageUI.PASSWORD_MISMATCH_ERROR);
        return getElementText(driver, AddEmployeePageUI.PASSWORD_MISMATCH_ERROR);
    }

    public String getUsernameLengthError() {
        waitForElementVisible(driver, AddEmployeePageUI.USERNAME_LESS_THAN_5_ERROR);
        return getElementText(driver, AddEmployeePageUI.USERNAME_LESS_THAN_5_ERROR);
    }

    public String getPasswordLengthError() {
        waitForElementVisible(driver, AddEmployeePageUI.PASSWORD_LESS_THAN_7_ERROR);
        return getElementText(driver, AddEmployeePageUI.PASSWORD_LESS_THAN_7_ERROR);
    }

    public String getUsernameExistsError() {
        waitForElementVisible(driver, AddEmployeePageUI.USERNAME_ALREADY_EXISTS_ERROR);
        return getElementText(driver, AddEmployeePageUI.USERNAME_ALREADY_EXISTS_ERROR);
    }
}
