package interfaces.pageUIs.PIM;

public class AddEmployeePageUI {
    // --- Navigation & Page Identification ---
    public static final String PIM_SECTION = "//span[text()='PIM']";
    public static final String ADD_EMPLOYEE_TAB = "//a[text()='Add Employee']";
    public static final String MAIN_TITLE = "//h6[contains(@class, 'orangehrm-main-title') and text()='Add Employee']";
    // --- Employee Information Section ---
    public static final String FIRST_NAME_INPUT = "//input[@name='firstName']";
    public static final String MIDDLE_NAME_INPUT = "//input[@name='middleName']";
    public static final String LAST_NAME_INPUT = "//input[@name='lastName']";
    public static final String EMPLOYEE_ID_INPUT = "//label[text()='Employee Id']/../following-sibling::div/input";
    public static final String IMAGE_AREA = "div.orangehrm-employee-image";
    public static final String IMPORT_IMAGE_INPUT = "//input[@type='file']"; // Hidden input for upload
    public static final String AVATAR_IMAGE = "//img[@class='employee-image']";
    public static final String DEFAULT_AVATAR_IMAGE_SRC_PART = "/web/images/default-photo.png"; // Part of src to check default
    // --- Create Login Details Section ---
    public static final String CREATE_LOGIN_DETAILS_TOGGLE = "//span[contains(@class, 'oxd-switch-input')]";
    public static final String USERNAME_INPUT = "//label[text()='Username']/../following-sibling::div/input";
    public static final String STATUS_ENABLED_RADIO_LABEL = "//label[normalize-space()='Enabled']"; // Click label is better
    public static final String STATUS_DISABLED_RADIO_LABEL = "//label[normalize-space()='Disabled']"; // Click label is better
    public static final String PASSWORD_INPUT = "//label[text()='Password']/parent::div/following-sibling::div/input"; // More specific password locator
    public static final String CONFIRM_PASSWORD_INPUT = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input"; // More specific confirm password
    // --- Actions ---
    public static final String SAVE_BUTTON = "//button[@type='submit']";
    public static final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']"; // Added Cancel button locator
    // --- Validation Messages ---
    // Generic locator strategy: find input, go up to group, find error span
    public static final String DYNAMIC_ERROR_EMPLOYEE_INFOR = "//input[@name='%s']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String DYNAMIC_ERROR_LOGIN_DETAILS = "//label[text()='%s']//ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]";

    public static final String FIRST_NAME_REQUIRED_ERROR = "//input[@name='firstName']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String LAST_NAME_REQUIRED_ERROR = "//input[@name='lastName']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String USERNAME_REQUIRED_ERROR = "//label[text()='Username']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String PASSWORD_REQUIRED_ERROR = "//label[text()='Password']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String CONFIRM_PASSWORD_REQUIRED_ERROR = "//label[text()='Confirm Password']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]";
    public static final String PASSWORD_MISMATCH_ERROR = "//label[text()='Confirm Password']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]"; // Check actual text in test
    public static final String USERNAME_LESS_THAN_5_ERROR = "//label[text()='Username']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]"; // Check actual text
    public static final String PASSWORD_LESS_THAN_7_ERROR = "//label[text()='Password']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]"; // Check actual text
    public static final String USERNAME_ALREADY_EXISTS_ERROR = "//label[text()='Username']/../following-sibling::div//span[contains(@class,'oxd-input-field-error-message')]"; // Check actual text or toast
    // --- Personal Details Page (After Save) ---
    // These should ideally be in a PersonalDetailsPageUI class
    public static final String PERSONAL_DETAILS_PAGE_TITLE = "//h6[text()='Personal Details']";
}
