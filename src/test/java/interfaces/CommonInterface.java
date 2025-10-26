package interfaces;

public class CommonInterface {
    public static final String HOME_PAGE_MENU = "//div[@class='card-body']/h5[normalize-space()='%s']";

    // Mục trong sidebar bên trái của trang Elements (chỉ các item đang show)
    public static final String ELEMENTS_MENU =
            "//div[contains(@class,'element-list') and contains(@class,'show')]//li//span[normalize-space()='%s']";
}
