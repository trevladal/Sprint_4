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
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.", 2},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру." +
                        " Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 3},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 4},
                {"Пока что нет! Но если что-то срочное" +
                        " — всегда можно позвонить в поддержку по красивому номеру 1010.", 5},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже" +
                        " если будете кататься без передышек и во сне. Зарядка не понадобится.", 6},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 7},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 8}
        };
    }

    @Test
    public void testCheckingTextInAccordion() {

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

