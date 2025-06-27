package interfaces.pageUIs.admin.job;

public class JobTitlesPageUI {
    public static final String ADMIN_MENU = "//span[text()='Admin']";
    public static final String JOB_MENU = "//span[text()='Job ']";
    public static final String JOB_TITLES_MENU = "//a[text()='Job Titles']";
    public static final String JOB_TITLES_LABEL = "//h6[text()='Job Titles']";
    public static final String ADD_JOB_TITLE_BUTTON = "//button[text()=' Add ']";
    public static final String ADD_JOB_TITLE_LABEL = "//h6[text()='Add Job Title']";
    public static final String JOB_TITLE_TEXTBOX = "//label[text()='Job Title']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//descendant::input";
    public static final String JOB_DESCRIPTION_INPUT = "//textarea[@placeholder='Type description here']";
    public static final String JOB_BROWSE_BUTTON = "//div[@class='oxd-file-button']";
    public static final String JOB_UPLOAD_FILE = "//input[@type='file']";
    public static final String JOB_NOTE = "//textarea[@placeholder='Add note']";
    public static final String SAVE_JOB_TITLE_BUTTON = "//button[text()=' Save ']";
    public static final String JOB_TITLE_NAME = "//div[@class='oxd-table-card']//div[@class='oxd-table-cell oxd-padding-cell']//div[normalize-space()='%s']";
    public static final String JOB_DESCRIPTION = "//span[text()='%s']";
    public static final String EDIT_JOB_TITLES_BUTTON = "//div[text()='%s']//ancestor::div[@role='row']//button[i[contains(@class, 'bi-pencil-fill')]]/i";
    public static final String DELETE_JOB_TITLE_BUTTON = "//div[text()='%s']//ancestor::div[@role='row']//button[i[contains(@class, 'bi-trash')]]/i";
    public static final String DELETE_CONFIRM_BUTTON = "//button[text()=' Yes, Delete ']";
    public static final String DELETE_SELECTED_BUTTON = "//button[text()=' Delete Selected ']";
    public static final String JOB_CHECKBOX = "//div[text()='%s']//parent::div/preceding-sibling::div//div[@class='oxd-table-card-cell-checkbox']";
}
