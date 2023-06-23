package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Homepage {
    //хэдер
    private By header = By.className("Header_Header__214zg");
    private final WebDriver driver;
    //кнопка заказа сверху
    private By orderButton = By.className("Button_Button__ra12g");
    //кнопка заказа снизу
    private By orderButtonBig = By.xpath(".//body/div/div/div/div/div/div/button");

    //блок с аккордионами
    private By accordionBlock = By.className("accordion");



    //конструктор
    public Homepage(WebDriver driver) {

        this.driver = driver;
    }



    //ждём прогрузку хэдера
    public void waitForLoadHomepage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    //ждём прогрузку аккордеона
    public void waitForAccordionLoad() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(accordionBlock));
    }

    //клик по аккордиону
    public void clickAccordion(int indexAccordion) {

        driver.findElement(By.xpath(".//div[@class ='accordion']/div[position() =" + indexAccordion +"]")).click();
    }

    //текст конкретного аккордиона
    public String getTextAccordion(int indexAccordion) {

        String a = driver.findElement(By.xpath(".//div[@class ='accordion']/div[position() =" + indexAccordion +"]/div/p")).getText();
        return a;
    }

    //листаем к блоку с аккордионом
    public void scrollToAccordion() {
        WebElement element = driver.findElement(accordionBlock);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //листаем к ултьтрабиг кнопке Заказать
    public void scrollToUltrabig() {
        WebElement element = driver.findElement(orderButtonBig);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //клик по кнопке заказа сверху
    public void clickOrderButton() {

        driver.findElement(orderButton).click();
    }

    //клик по кнопке заказа снизу
    public void clickUltraBigButton() {
        scrollToUltrabig();
        driver.findElement(orderButtonBig).click();
    }
    //клик по конкретной точке входа
    public void clickEntryPoint(String indexEntryPoint) {
        if (indexEntryPoint == "headerButtonEntryPoint") {
            clickOrderButton();
        } else if (indexEntryPoint == "ultraBigButtonEntryPoint" ) {
            clickUltraBigButton();
        }
    }
}