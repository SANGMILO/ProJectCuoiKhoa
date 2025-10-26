package interfaces.elements;

public interface DatePickerPageInterface {
    String DATE_INPUT = "//*[@id='datePickerMonthYearInput']";

    String DAY_CELL_BY_NUM = "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and normalize-space()='%s']";
}
