package actions.pageObject.admin.organization;

import actions.commons.BasePage;
import interfaces.pageUIs.admin.organization.StructurePageUI;
import org.openqa.selenium.WebDriver;

public class StructurePageObject extends BasePage {
    private WebDriver driver;

    public StructurePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAdminSection() {
        waitForElementClickable(driver, StructurePageUI.ADMIN_SECTION);
        clickToElement(driver, StructurePageUI.ADMIN_SECTION);
    }

    public void clickToOrganization() {
        waitForElementClickable(driver, StructurePageUI.ORGANIZATION_ITEM);
        clickToElement(driver, StructurePageUI.ORGANIZATION_ITEM);
    }

    public void clickToStructureOption() {
        waitForElementClickable(driver, StructurePageUI.STRUCTURE_ITEM);
        clickToElement(driver, StructurePageUI.STRUCTURE_ITEM);
    }

    public String getMainTitle() {
        waitForElementVisible(driver, StructurePageUI.MAIN_TITLE);
        return getElementText(driver, StructurePageUI.MAIN_TITLE);
    }

    public void clickToEditToggle() {
        waitForElementClickable(driver, StructurePageUI.EDIT_TOGGLE);
        clickToElement(driver, StructurePageUI.EDIT_TOGGLE);
    }
    public void clickToAddBtn() {
        waitForElementClickable(driver, StructurePageUI.ADD_BTN);
        clickToElement(driver, StructurePageUI.ADD_BTN);
    }
    public boolean isAddDialogDisplayed(){
        waitForElementVisible(driver, StructurePageUI.ADD_ORG_DIALOG);
        return isElementDisplayed(driver, StructurePageUI.ADD_ORG_DIALOG);
    }
    private void enterText(String locator, String value){
        waitForElementVisible(driver, locator);
        sendkeyToElement(driver, locator, value);
    }
    public void enterUnitId(String unitId) {
        enterText(StructurePageUI.INPUT_UNIT_ID, unitId);
    }

    public void enterName(String name) {
        enterText(StructurePageUI.INPUT_NAME, name);
    }

    public void enterDescription(String description) {
        enterText(StructurePageUI.INPUT_DESCRIPTION, description);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, StructurePageUI.SAVE_BTN);
        clickToElement(driver, StructurePageUI.SAVE_BTN);
    }

    public String getMainSuccessMessage() {
        waitForElementVisible(driver, StructurePageUI.SUCCESS_TOAST);
        waitForElementVisible(driver, StructurePageUI.SUCCESS_MAIN_TOAST);
        return getElementText(driver, StructurePageUI.SUCCESS_MAIN_TOAST);
    }
    public String getSubSuccessMessage(){
        waitForElementVisible(driver, StructurePageUI.SUCCESS_SUB_TOAST);
        return getElementText(driver, StructurePageUI.SUCCESS_SUB_TOAST);
    }
    public boolean isOrganizationUnitDisplayed(String unitName) {
        try {
            waitForElementVisible(driver, getDynamicLocator(StructurePageUI.UNIT, unitName));
            if (isElementDisplayed(driver, getDynamicLocator(StructurePageUI.UNIT, unitName))) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Element with unit name '" + unitName + "' not found. Error: " + e.getMessage());
        }
        return false;
    }
//    public boolean isOrganizationUnitDisplayed(String unitName){
//        try(isElementDisplayed(driver, getDynamicLocator(StructurePageUI.UNIT, unitName))) {
//            waitForElementVisible(driver, getDynamicLocator(StructurePageUI.UNIT, unitName));
//            return true;
//        }
//        return false;
//    }
//    public boolean isOrganizationUnitNotDisplayed(String unitName){
//        if(!isElementDisplayed(driver, getDynamicLocator(StructurePageUI.UNIT, unitName))) {
//            return true;
//        }
//        return false;
//    }

    public void clickToDeleteIcon(String unitName) {
        waitForElementClickable(driver, getDynamicLocator(StructurePageUI.DELETE_ICON, unitName));
        clickToElement(driver, getDynamicLocator(StructurePageUI.DELETE_ICON, unitName));
    }

    public void confirmDelete() {
        waitForElementClickable(driver, StructurePageUI.YES_BTN);
        clickToElement(driver, StructurePageUI.YES_BTN);
    }

    public void clickAddSubOrganizationIcon(String parentUnitName) {
        waitForElementClickable(driver, getDynamicLocator(StructurePageUI.PLUS_ICON, parentUnitName));
        clickToElement(driver, getDynamicLocator(StructurePageUI.PLUS_ICON, parentUnitName));
    }

    public boolean isEditToggleDisplayed() {
        return isElementDisplayed(driver, StructurePageUI.EDIT_TOGGLE);
    }

    public boolean isAddButtonDisplayed() {
        return isElementDisplayed(driver, StructurePageUI.ADD_BTN);
    }

    public void clickToExtendIcon(String unitName) {
        waitForElementVisible(driver, getDynamicLocator(StructurePageUI.UNIT, unitName));
        waitForElementClickable(driver, getDynamicLocator(StructurePageUI.EXTEND_ICON, unitName));
        clickToElement(driver, getDynamicLocator(StructurePageUI.EXTEND_ICON, unitName));
    }

    public boolean isRequiredFieldErrorDisplayed(String name) {
        return isElementDisplayed(driver, StructurePageUI.ERROR_REQUIRED_NAME);
    }
}