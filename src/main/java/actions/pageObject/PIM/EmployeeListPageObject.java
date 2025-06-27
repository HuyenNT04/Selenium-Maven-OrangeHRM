package actions.pageObject.PIM;

import actions.commons.BasePage;
import interfaces.pageUIs.PIM.EmployeeListPageUI;
import org.openqa.selenium.WebDriver;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;
    //Constructor
    public EmployeeListPageObject (WebDriver driver){
        this.driver = driver;
    }

    public void enterEmployeeId(String employeeId) {
        waitForElementClickable(driver, EmployeeListPageUI.EMPLOYEE_ID_INPUT);
        sendkeyToElement(driver, EmployeeListPageUI.EMPLOYEE_ID_INPUT, employeeId);
    }

    public void clickToSearchBtn() {
        waitForElementClickable(driver, EmployeeListPageUI.SEARCH_BUTTON);
        clickToElement(driver, EmployeeListPageUI.SEARCH_BUTTON);
    }

    public boolean isEmployeeDisplay(String employeeId, String firstName, String middleName, String lastName) {
        return isElementDisplayed(driver, getDynamicLocator(EmployeeListPageUI.TEXT_SEARCH_RESULT, employeeId))
                && isElementDisplayed(driver, getDynamicLocator(EmployeeListPageUI.TEXT_SEARCH_RESULT, firstName + " " + middleName))
                && isElementDisplayed(driver, getDynamicLocator(EmployeeListPageUI.TEXT_SEARCH_RESULT, lastName));
    }

    public void clickToEditIcon(String employeeId) {
        waitForElementClickable(driver, getDynamicLocator(EmployeeListPageUI.EDIT_ICON, employeeId));
        clickToElement(driver, getDynamicLocator(EmployeeListPageUI.EDIT_ICON, employeeId));
    }
}
