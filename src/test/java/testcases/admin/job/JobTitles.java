package testcases.admin.job;


import actions.commons.BaseTest;
import actions.pageObject.admin.job.JobTitlesPageObject;
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

import java.io.File;

import static actions.commons.GlobalConstants.uploadFolderPath;

@Listeners(AllureReportListener.class)
@Epic("Check Job Titles")
@Feature("Check demo Feature Job Titles")
public class JobTitles extends BaseTest {
     private static ThreadLocal<JobTitlesPageObject> jobTitlesPageObjectThreadLocal  = new ThreadLocal<>();
    String name, des, note, name2;
    String specFileName = "8888.png";
    String specFilePath = uploadFolderPath + specFileName;
    @BeforeMethod(alwaysRun = true)
    @Description("Open Job Titles Page")
    public void beforeClass(ITestContext context) {
        WebDriver currentDriver = getDriver();
        JobTitlesPageObject page = new JobTitlesPageObject(currentDriver);
        page.clickToAdminMenu();
        page.clickToJobMenu();
        page.clickToJobTitlesMenu();
        jobTitlesPageObjectThreadLocal.set(page);
    }
    @Test
    @Step("JT_01_CheckUI")
    public void JT_01_CheckUI() {
        JobTitlesPageObject jobTitlesPage = jobTitlesPageObjectThreadLocal.get();
        verifyEquals(jobTitlesPage.getJobTitlesLabel(),"Job Titles");
        verifyTrue(jobTitlesPage.isAddBtnDisplayed());
    }

    @Test
    @Step("JT_02_User_Create_New_Job_Title")
    public void JT_02_User_Create_New_Job_Title() {
        JobTitlesPageObject jobTitlesPage = jobTitlesPageObjectThreadLocal.get();
        //data
        name = generateRandomName();
        des = generateRandomName() + " check Des";
        note = generateRandomName() + " check Note";
        //Create
        jobTitlesPage.clickToAddJobTitleButton();
        verifyEquals(jobTitlesPage.getAddJobTitlesLabel(), "Add Job Title");
        jobTitlesPage.enterName(name);
        jobTitlesPage.enterJobDescription(des);
        jobTitlesPage.uploadJobSpecification(specFilePath);
        jobTitlesPage.enterNote(note);
        jobTitlesPage.clickToSaveJobTitleButton();
        //Verify
        verifyEquals(jobTitlesPage.getMainSuccessMessage(),"Success");
        verifyEquals(jobTitlesPage.getSubSuccessMessage(),"Successfully Saved");
        verifyEquals(jobTitlesPage.getJobTitlesLabel(),"Job Titles");
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name));
        verifyTrue(jobTitlesPage.isJobDescriptionDisplayed(des));
    }

    @Test
    @Step("JT_03_User_Update_Job_Title")
    public void JT_03_User_Update_Job_Title() {
        JobTitlesPageObject jobTitlesPage = jobTitlesPageObjectThreadLocal.get();
        //data
        name = generateRandomName();
        des = generateRandomName() + " check Update";
        name2 = name + "Updated";
        //Add
        jobTitlesPage.clickToAddJobTitleButton();
        jobTitlesPage.enterName(name);
        jobTitlesPage.clickToSaveJobTitleButton();
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name));
        // Edit
        jobTitlesPage.clickToEditJobTitle(name);
        jobTitlesPage.enterName(name2);
        jobTitlesPage.enterJobDescription(des);
        jobTitlesPage.clickToSaveJobTitleButton();
        //Verify
        verifyEquals(jobTitlesPage.getMainSuccessMessage(),"Success");
        verifyEquals(jobTitlesPage.getSubSuccessMessage(),"Successfully Updated");
        verifyEquals(jobTitlesPage.getJobTitlesLabel(),"Job Titles");
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name2));
        verifyTrue(jobTitlesPage.isJobDescriptionDisplayed(des));
    }

    @Test
    @Step("JT_04_User_Delete_Job_Title")
    public void JT_04_User_Delete_Job_Title() {
        JobTitlesPageObject jobTitlesPage = jobTitlesPageObjectThreadLocal.get();
        //Create
        name = generateRandomName();
        jobTitlesPage.clickToAddJobTitleButton();
        jobTitlesPage.enterName(name);
        jobTitlesPage.clickToSaveJobTitleButton();
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name));
        //Delete
        jobTitlesPage.clickToDeleteJobTitle(name);
        jobTitlesPage.confirmDelete();
        //Verify
        verifyEquals(jobTitlesPage.getMainSuccessMessage(),"Success");
        verifyEquals(jobTitlesPage.getSubSuccessMessage(),"Successfully Deleted");
        verifyFalse(jobTitlesPage.isJobTitleDisplayed(name));
    }

    @Test
    @Step("JT_05_User_Delete_Multiple_Job_Titles")
    public void JT_05_User_Delete_Multiple_Job_Titles() {
        JobTitlesPageObject jobTitlesPage = jobTitlesPageObjectThreadLocal.get();
        //Create
        name = generateRandomName();
        jobTitlesPage.clickToAddJobTitleButton();
        jobTitlesPage.enterName(name);
        jobTitlesPage.clickToSaveJobTitleButton();
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name));

        name2 = generateRandomName();
        jobTitlesPage.clickToAddJobTitleButton();
        jobTitlesPage.enterName(name2);
        jobTitlesPage.clickToSaveJobTitleButton();
        verifyTrue(jobTitlesPage.isJobTitleDisplayed(name2));
        //Checked
        jobTitlesPage.selectOnCheckbox(name);
        jobTitlesPage.selectOnCheckbox(name2);
        jobTitlesPage.clickToDeleteSelectedButton();
        jobTitlesPage.confirmDelete();
        //Verify
        verifyFalse(jobTitlesPage.isJobTitleDisplayed(name));
        verifyFalse(jobTitlesPage.isJobTitleDisplayed(name2));
    }
    
}
