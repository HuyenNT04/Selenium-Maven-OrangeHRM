package actions.pageObject.myInfo;

import actions.commons.BasePage;
import interfaces.pageUIs.myInfo.PersonalDetailPageUI;
import org.openqa.selenium.WebDriver;

public class PersonalDetailPageObject extends BasePage {
    private WebDriver driver;
    public PersonalDetailPageObject (WebDriver driver){
        this.driver = driver;
    }
    //co 3 cach access --> My in4, add done and click on Detail from list
    public String getMainTitle(){
        waitForElementVisible(driver, PersonalDetailPageUI.mainTitle);
        return getElementText(driver, PersonalDetailPageUI.mainTitle);
    }
    public String getFirstName(){
        return getValueFromDOMProperty(driver, PersonalDetailPageUI.FIRSTNAME_VALUE);
    }

    public String getMiddleName() {
        return getValueFromDOMProperty(driver, PersonalDetailPageUI.MIDDLENAME_VALUE);
    }

    public String getLastName() {
        return getValueFromDOMProperty(driver, PersonalDetailPageUI.LASTNAME_VALUE);
    }

    public String getEmployeeId() {
        return getValueFromDOMProperty(driver, PersonalDetailPageUI.EMPLOYEEID_VALUE);
    }

    public void editFirstName(String updatedValue){
        waitForStableInputValue(driver, PersonalDetailPageUI.FIRSTNAME_VALUE);
        sendkeyToElement(driver, PersonalDetailPageUI.FIRSTNAME_VALUE, updatedValue);
    }

    public void editMiddleName(String updatedValue) {
        waitForStableInputValue(driver, PersonalDetailPageUI.MIDDLENAME_VALUE);
        sendkeyToElement(driver, PersonalDetailPageUI.MIDDLENAME_VALUE, updatedValue);
    }

    public void editLastName(String updatedValue) {
        waitForStableInputValue(driver, PersonalDetailPageUI.LASTNAME_VALUE);
        sendkeyToElement(driver, PersonalDetailPageUI.LASTNAME_VALUE, updatedValue);
    }

    public void editEmployeeId(String updatedValue) {
        waitForStableInputValue(driver, PersonalDetailPageUI.EMPLOYEEID_VALUE);
        sendkeyToElement(driver, PersonalDetailPageUI.EMPLOYEEID_VALUE, updatedValue);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, PersonalDetailPageUI.SAVE_BUTTON_EMPLOYEE_DETAILS);
        clickToElement(driver, PersonalDetailPageUI.SAVE_BUTTON_EMPLOYEE_DETAILS);
    }

}
