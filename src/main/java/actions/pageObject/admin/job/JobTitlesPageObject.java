package actions.pageObject.admin.job;

import actions.commons.BasePage;
import interfaces.pageUIs.BasePageUI;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.admin.job.JobTitlesPageUI;
public class JobTitlesPageObject extends BasePage {
    private WebDriver driver;

    public JobTitlesPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAdminMenu() {
        waitForElementClickable(driver, JobTitlesPageUI.ADMIN_MENU);
        clickToElement(driver, JobTitlesPageUI.ADMIN_MENU);
    }

    public void clickToJobMenu() {
        waitForElementClickable(driver, JobTitlesPageUI.JOB_MENU);
        clickToElement(driver, JobTitlesPageUI.JOB_MENU);
    }

    public void clickToJobTitlesMenu() {
        waitForElementClickable(driver, JobTitlesPageUI.JOB_TITLES_MENU);
        clickToElement(driver, JobTitlesPageUI.JOB_TITLES_MENU);
    }

    public void clickToAddJobTitleButton() {
        waitForElementClickable(driver, JobTitlesPageUI.ADD_JOB_TITLE_BUTTON);
        clickToElement(driver, JobTitlesPageUI.ADD_JOB_TITLE_BUTTON);
    }

    public void enterName(String jobTitle) {
        waitForElementVisible(driver, JobTitlesPageUI.JOB_TITLE_TEXTBOX);
        sendkeyToElement(driver, JobTitlesPageUI.JOB_TITLE_TEXTBOX, jobTitle);
    }

    public void enterJobDescription(String description) {
        waitForElementVisible(driver, JobTitlesPageUI.JOB_DESCRIPTION_INPUT);
        sendkeyToElement(driver, JobTitlesPageUI.JOB_DESCRIPTION_INPUT, description);
    }
    public void uploadJobSpecification(String path){
        waitForElementClickable(driver, JobTitlesPageUI.JOB_BROWSE_BUTTON);
        sendkeyToUpload(driver, JobTitlesPageUI.JOB_UPLOAD_FILE, path);
    }
    public void enterNote(String note) {
        waitForElementVisible(driver, JobTitlesPageUI.JOB_NOTE);
        sendkeyToElement(driver, JobTitlesPageUI.JOB_NOTE, note);
    }
    public void clickToSaveJobTitleButton() {
        waitForElementClickable(driver, JobTitlesPageUI.SAVE_JOB_TITLE_BUTTON);
        clickToElement(driver, JobTitlesPageUI.SAVE_JOB_TITLE_BUTTON);
    }

    public boolean isJobTitleDisplayed(String jobTitle) {
            try {
                waitForElementVisible(driver, getDynamicLocator(JobTitlesPageUI.JOB_TITLE_NAME, jobTitle));
                if(isElementDisplayed(driver,getDynamicLocator(JobTitlesPageUI.JOB_TITLE_NAME, jobTitle))){
                    return true;
                }
            }catch (Exception e) {
                System.out.println("Job title not displayed: " + jobTitle + " | Error: " + e.getMessage());
            }
            return false;
    }

    public boolean isJobDescriptionDisplayed(String des) {
        if(isElementDisplayed(driver, getDynamicLocator(JobTitlesPageUI.JOB_DESCRIPTION, des))){
            return true;
        }
        return false;
    }

    public void clickToEditJobTitle(String jobTitle) {
        waitForElementClickable(driver, getDynamicLocator(JobTitlesPageUI.EDIT_JOB_TITLES_BUTTON, jobTitle));
        clickToElement(driver, getDynamicLocator(JobTitlesPageUI.EDIT_JOB_TITLES_BUTTON, jobTitle));
        waitForStableInputValue(driver,JobTitlesPageUI.JOB_TITLE_TEXTBOX);
    }

    public void clickToDeleteJobTitle(String jobTitle) {
        waitForElementClickable(driver, getDynamicLocator(JobTitlesPageUI.DELETE_JOB_TITLE_BUTTON, jobTitle));
        clickToElement(driver, getDynamicLocator(JobTitlesPageUI.DELETE_JOB_TITLE_BUTTON, jobTitle));
    }

    public void confirmDelete() {
        waitForElementClickable(driver, JobTitlesPageUI.DELETE_CONFIRM_BUTTON);
        clickToElement(driver, JobTitlesPageUI.DELETE_CONFIRM_BUTTON);
    }

    public String getMainSuccessMessage() {
        waitForElementVisible(driver, BasePageUI.SUCCESS_TOAST);
        waitForElementVisible(driver, BasePageUI.SUCCESS_MAIN_TOAST);
        return getElementText(driver, BasePageUI.SUCCESS_MAIN_TOAST);
    }

    public String getSubSuccessMessage(){
        waitForElementVisible(driver, BasePageUI.SUCCESS_SUB_TOAST);
        return getElementText(driver, BasePageUI.SUCCESS_SUB_TOAST);
    }

    public String getJobTitlesLabel() {
        waitForElementVisible(driver, JobTitlesPageUI.JOB_TITLES_LABEL);
        return getElementText(driver,JobTitlesPageUI.JOB_TITLES_LABEL);
    }
    public String getAddJobTitlesLabel() {
        waitForElementVisible(driver, JobTitlesPageUI.ADD_JOB_TITLE_LABEL);
        return getElementText(driver,JobTitlesPageUI.ADD_JOB_TITLE_LABEL);
    }

    public boolean isAddBtnDisplayed(){
        return isElementDisplayed(driver, JobTitlesPageUI.ADD_JOB_TITLE_BUTTON);
    }
    public void selectOnCheckbox(String name){
        waitForElementVisible(driver, getDynamicLocator(JobTitlesPageUI.JOB_TITLE_NAME, name));
        clickToElement(driver,getDynamicLocator(JobTitlesPageUI.JOB_CHECKBOX, name));
    }
    public void clickToDeleteSelectedButton(){
        //waitForElementVisible(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
        //waitForElementPresence(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
        //clickToElement(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON); --> khi scroll xuong thi khong pass
        waitForElementClickable(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
    //    clickToElementByActions(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
        scrollToElement(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
        clickToElement(driver, JobTitlesPageUI.DELETE_SELECTED_BUTTON);
    }
}
