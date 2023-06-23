import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.Homepage;
import page_object.Orderpage;


@RunWith(Parameterized.class)
public class OrderScooter {

    private WebDriver driver;

    private final String valueEntryPoint;
    private final String name;
    private final String secondName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final int rentIndex;
    private final Boolean isBlackCheckboxActive;
    private final Boolean isGreyCheckboxActive;
    private final String comment;

    public OrderScooter(String valueEntryPoint, String name, String secondName, String address, String station,
                               String phone, String date, int rentIndex, Boolean isBlackCheckboxActive,
                               Boolean isGreyCheckboxActive, String comment) {

        this.valueEntryPoint = valueEntryPoint;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rentIndex = rentIndex;
        this.isBlackCheckboxActive = isBlackCheckboxActive;
        this.isGreyCheckboxActive = isGreyCheckboxActive;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] checkOrder() {

        return new Object[][]{
                {"headerButtonEntryPoint", "Алексей", "Алексеев", "Пушкина 20", "Митино", "+79999999999", "14.06.2023", 1, true, false, "тест123"},
                {"ultraBigButtonEntryPoint", "Сергей", "Сергеев", "Колотушкина 20", "Сокольники", "+79000000000", "15.07.2023", 2, true, true, "тест456"}
        };
    }

    @Test
    public void test() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаём объект класса страницы заказа
        Orderpage objOrderpage = new Orderpage(driver);
        //создаём объект класса главной страницы
        Homepage objHomepage = new Homepage(driver);
        //ждём загрузки главной страницы
        objHomepage.waitForLoadHomepage();
        //нажимаем Заказать сверху (1 точка входа) //продумать как повторить тест со второй точкой входа
        objHomepage.clickEntryPoint(valueEntryPoint);
        //ждём загрузки страницы с заказом
        objOrderpage.waitForLoadOrderContent();

        //заполняем поля
        objOrderpage.setName(name);
        objOrderpage.setSecondName(secondName);
        objOrderpage.setAddress(address);
        objOrderpage.setStation(station);
        objOrderpage.clickLastElement();
        objOrderpage.setPhone(phone);

        //Нажимаем далее
        objOrderpage.clickNextButton();

        objOrderpage.setDate(date);
        objOrderpage.setRentTime(rentIndex);
        objOrderpage.clickCheckboxBlack(isBlackCheckboxActive);
        objOrderpage.clickCheckboxGrey(isGreyCheckboxActive);
        objOrderpage.setComment(comment);

        //клик по кнопке "Заказать" под заполненными полями
        objOrderpage.clickOrderButtonFinal();

        //клик по кнопке "Да", здесь баг
        objOrderpage.clickYesButtonFinal();

        //ждём появление модалки с успешным заказом
        objOrderpage.waitOrderModal2();

    }

    @After
    public void teardown() {
        driver.quit();
    }
}