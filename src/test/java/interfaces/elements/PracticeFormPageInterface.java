package interfaces.elements;

public interface PracticeFormPageInterface {
    String FIRST_NAME = "//*[@id='firstName']";
    String LAST_NAME  = "//*[@id='lastName']";
    String EMAIL      = "//*[@id='userEmail']";

    String GENDER_MALE_LABEL = "//label[@for='gender-radio-1']";
    String MOBILE    = "//*[@id='userNumber']";
    String SUBMIT    = "//*[@id='submit']";

    String MODAL_TITLE = "//*[@id='example-modal-sizes-title-lg']";
    String RESULT_TABLE = "//div[@class='table-responsive']//table";
    String RESULT_CELL_BY_LABEL = RESULT_TABLE + "//td[normalize-space()='%s']/following-sibling::td";
}
