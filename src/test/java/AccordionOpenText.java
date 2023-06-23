import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.Homepage;

import static org.hamcrest.CoreMatchers.is;


@RunWith(Parameterized.class)
public class AccordionOpenText {
    private WebDriver driver;
    private final String textAccordion;
    private final int indexAccordion;


    public AccordionOpenText(String textAccordion, int indexAccordion) {

        this.textAccordion = textAccordion;
        this.indexAccordion = indexAccordion;
    }

    @Parameterized.Parameters
    public static Object[][] checkText() {

        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 1},
                {"Пока что нет! Но если что-то срочное" +
                        " — всегда можно позвонить в поддержку по красивому номеру 1010.", 5},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 8}
        };
    }

    @Test
    public void test() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаём объект класса главной страницы
        Homepage objHomepage = new Homepage(driver);
        //ждём прогрузку аккордиона
        objHomepage.waitForAccordionLoad();
        //листаем к аккордиону
        objHomepage.scrollToAccordion();
        //Нажимаем аккордион
        objHomepage.clickAccordion(indexAccordion);
        //Получаем текст аккордиона
        objHomepage.getTextAccordion(indexAccordion);
        //Ждём и сравниваем текст
        MatcherAssert.assertThat(textAccordion, is(objHomepage.getTextAccordion(indexAccordion)));

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}

