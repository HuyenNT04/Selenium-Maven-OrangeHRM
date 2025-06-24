package interfaces.pageUIs.admin.qualification;

public class EducationPageUI {
    public static final String ADMIN_MENU = "//span[text()='Admin']";
    public static final String QUALIFICATIONS_MENU = "//span[normalize-space()='Qualifications']";
    public static final String EDUCATION_MENU = "//a[normalize-space()='Education']";
    public static final String MAIN_TITLE = "//h6[contains(@class,'orangehrm-main-title') and text() = 'Education']";
    public static final String ADD_MAIN_TITLE = "//h6[contains(@class,'orangehrm-main-title') and text() = 'Add Education']";
    public static final String EDIT_MAIN_TITLE = "//h6[contains(@class,'orangehrm-main-title') and text() = 'Edit Education']";
    public static final String ADD_LEVEL_BUTTON = "//button[normalize-space()='Add']";
    public static final String LEVEL_INPUT = "//label[text()='Level']//ancestor::div[@class='oxd-form-row']//descendant::input";
    public static final String SAVE_LEVEL_BUTTON = "//button[@type='submit']";
    public static final String DELETE_DIALOG = "//p[text()='Are you Sure?']";
    public static final String DELETE_SELECTED_BUTTON = "//button[normalize-space()='Delete Selected']";
    public static final String YES_DELETE_BUTTON = "//button[normalize-space()='Yes, Delete']";
    public static final String ERROR_UNDER_INPUT_NAME = "//span[contains(@class, 'oxd-input-field-error-message')]";


    // Dynamic locators
    public static String CHECK_BOX_EDUCATION = "//div[contains(text(),'%s')]//ancestor::div[@role='row']//descendant::div[@class='oxd-checkbox-wrapper']";
    public static String DELETE_ICON= "//div[text()='%s']//ancestor::div[@role='row']//descendant::i[@class='oxd-icon bi-trash']";
    public static String EDIT_ICON= "//div[text()='%s']//ancestor::div[@role='row']//descendant::i[@class='oxd-icon bi-pencil-fill']";
    public static String NEW_EDUCATION = "//div[text()='%s']";
}
