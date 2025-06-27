package interfaces.pageUIs.myInfo;

public class PersonalDetailPageUI {
    public static final String mainTitle = "//h6[contains(@class, 'orangehrm-main-title') and text()='Personal Details']";
    public static final String FIRSTNAME_VALUE = "//input[@name='firstName']";
    public static final String MIDDLENAME_VALUE = "//input[@name='middleName']";
    public static final String LASTNAME_VALUE = "//input[@name='lastName']";
    public static final String EMPLOYEEID_VALUE = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON_EMPLOYEE_DETAILS = "//div[contains(@class, 'orangehrm-horizontal-padding')]//button[@type = 'submit']";
}
