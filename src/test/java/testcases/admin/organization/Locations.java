package testcases.admin.organization;

import actions.commons.BaseTest;
import actions.pageObject.admin.organization.LocationsPageObject;
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
@Epic("Check Location")
@Feature("Check demo Feature Location")
public class Locations extends BaseTest {
    private static ThreadLocal<LocationsPageObject> locationsPageThreadLocal = new ThreadLocal<>();
    //private String[] createdLocationInfo;

    @BeforeMethod(alwaysRun = true)
    @Description("Open Location Page")
    public void beforeClass(ITestContext context){
        WebDriver currentDriver = getDriver();
        LocationsPageObject page = new LocationsPageObject(currentDriver);
        page.clickToAdminSection();
        page.clickToOrganization();
        page.clickToLocationOption();
        locationsPageThreadLocal.set(page);
    }

    @Test
    @Step("Check UI")
    public void LO_01_CheckUI(){
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        verifyEquals(locationsPage.getLabel(),"Locations");
        verifyTrue(locationsPage.isAddButtonDisplayed());
    }
    @Test()
    @Step("Create a new Location")
    public void LO_02_CreateNewLocation(){
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        //data
        String name, city, country;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        //Add new
        locationsPage.clickToAddBtn();
        name = generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getSubSuccessMessage(),"Successfully Saved");
        verifyEquals(locationsPage.getLabel(),"Locations");
        //createdLocationInfo = new String[]{name, city, country};
    }
    @Test()
    @Step("Search by Name")
    public void LO_03_SearchByName() {
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        //Create data
        String name, city, country;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        //Add new
        locationsPage.clickToAddBtn();
        name = generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getLabel(),"Locations");
        //Verify
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        verifyTrue(locationsPage.isNameSearchContained(name));
    }
    @Test()
    @Step("Search by City")
    public void LO_04_SearchByCity(){
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        //Create data
        String name, city, country;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        //Add new
        locationsPage.clickToAddBtn();
        name = generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getLabel(),"Locations");
        //Verify
        locationsPage.enterCity(city);
        locationsPage.clickToSearchBtn();
        verifyTrue(locationsPage.isCitySearchContained(city));
    }

    @Test()
    @Step("Search by Country")
    public void LO_05_SearchByCountry(){
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        //Create data
        String name, city, country;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        //Add new
        locationsPage.clickToAddBtn();
        name = generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getLabel(),"Locations");
        //Verify
        locationsPage.selectCountry(country);
        locationsPage.clickToSearchBtn();
        verifyTrue(locationsPage.isCountrySearchContained(country));
    }

    @Test
    @Step("Update an existing name")
    public void LO_06_UpdateLocation(){
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        String name, name2, city, country, city2, country2;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        city2 = "update" + getRandomNumber();
        country2 = "Algeria";
        // 🛠 Create
        locationsPage.clickToAddBtn();
        name = generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getSubSuccessMessage(),"Successfully Saved");

        //Search
        verifyEquals(locationsPage.getLabel(),"Locations");
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        locationsPage.clickToEditBtn(name);

        //Edit
        verifyEquals(locationsPage.getEditTitle(),"Edit Location");
        name2 = generateRandomName();
        locationsPage.changeName(name2);
        locationsPage.changeCity(city2);
        locationsPage.selectOtherCountry(country2);
        locationsPage.clickToSaveBtn();

        //Verify to update successfully
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getSubSuccessMessage(),"Successfully Updated");
        verifyEquals(locationsPage.getLabel(),"Locations");

        //--Old data is disappeared
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        verifyTrue(locationsPage.isDisplayedNoResultText());

        //--New data should be displayed
        locationsPage.enterName(name2);
        locationsPage.clickToSearchBtn();
        sleepInSeconds(500);
        verifyTrue(locationsPage.isNameSearchContained(name2));
        verifyTrue(locationsPage.isCitySearchContained(city2));
        verifyTrue(locationsPage.isCountrySearchContained(country2));
    }

    @Test
    @Step("Create and Delete a single location")
    public void LO_07_DeleteSingleLocation() {
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        String name, city, country;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        // 🛠 Create data
        locationsPage.clickToAddBtn();
        name = "nameToDelete1" + generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getLabel(),"Locations");

        // 🛠 Find and Delete
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        locationsPage.clickToDeleteIcon(name);
        locationsPage.clickYesToDelete();

        // ✅ Check delete
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getSubSuccessMessage(),"Successfully Deleted");

        //Check data after deleting
        verifyEquals(locationsPage.getLabel(),"Locations");
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        verifyFalse(locationsPage.isNameSearchContained(name));
    }

    @Test(groups = "runnow")
    @Step("Create and Delete multiple locations")
    public void LO_08_DeleteMultipleLocations() {
        LocationsPageObject locationsPage = locationsPageThreadLocal.get();
        String name, name2, city, country, city2, country2;
        city = "HaNoi" + getRandomNumber();
        country = "Viet Nam";
        city2 = "update" + getRandomNumber();
        country2 = "Algeria";
        // 🛠 Create data
        locationsPage.clickToAddBtn();
        name = "name1" + generateRandomName();
        locationsPage.addName(name);
        locationsPage.addCity(city);
        locationsPage.addCountry(country);
        locationsPage.clickToSaveBtn();

        verifyEquals(locationsPage.getLabel(),"Locations");
        locationsPage.clickToAddBtn();
        name2 = "name2" + generateRandomName();
        locationsPage.addName(name2);
        locationsPage.addCity(city2);
        locationsPage.addCountry(country2);
        locationsPage.clickToSaveBtn();

        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getLabel(),"Locations");

        // 🛠 Select many checkboxes
        locationsPage.checkOnCheckbox(name);
        locationsPage.checkOnCheckbox(name2);

        //Delete
        locationsPage.clickToDeleteBtn();
        locationsPage.clickYesToDelete();

        // ✅ check deleted successfully
        verifyEquals(locationsPage.getMainSuccessMessage(),"Success");
        verifyEquals(locationsPage.getSubSuccessMessage(),"Successfully Deleted");

        //Check data after deleting
        verifyEquals(locationsPage.getLabel(),"Locations");
        locationsPage.enterName(name);
        locationsPage.clickToSearchBtn();
        verifyFalse(locationsPage.isNameSearchContained(name));
        locationsPage.enterName(name2);
        locationsPage.clickToSearchBtn();
        verifyFalse(locationsPage.isNameSearchContained(name2));
    }
}
