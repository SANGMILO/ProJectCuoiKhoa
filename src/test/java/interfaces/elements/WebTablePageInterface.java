package interfaces.elements;

public interface WebTablePageInterface {
    String ROW_BY_FIRST_NAME = "//div[@role='rowgroup']//div[@role='gridcell'][1][normalize-space()='%s']/ancestor::div[@role='row']";
    String EMAIL_IN_SAME_ROW = ROW_BY_FIRST_NAME + "//div[@role='gridcell'][4]";

    String ROW_BY_LAST_NAME = "//div[@role='rowgroup']//div[@role='gridcell'][2][normalize-space()='%s']/ancestor::div[@role='row']";
    String EDIT_BTN_IN_ROW_BY_LASTNAME = ROW_BY_LAST_NAME + "//span[@title='Edit']";

    String EDIT_BTN_IN_ROW_BY_FIRSTNAME = ROW_BY_FIRST_NAME + "//span[@title='Edit']";

    String ROW_BY_AGE = "//div[@role='rowgroup']//div[@role='gridcell'][3][normalize-space()='%s']/ancestor::div[@role='row']";
    String FOLLOWING_ROWS_FROM_AGE = ROW_BY_AGE + "/following::div[@role='rowgroup']//div[@role='row']";

    String EDIT_MODAL = "//div[contains(@class,'modal-content')]";
}
