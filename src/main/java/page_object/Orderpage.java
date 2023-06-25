package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Orderpage {
    private WebDriver driver;

    //блок с заказом
    private By orderContent = By.className("Order_Content__bmtHS");
    //поле Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //поле Фамилия
    private By secondNameField = By.xpath(".//input[@placeholder ='* Фамилия']");
    //поле Адрес
    private By addressField = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    //поле Метро
    private By stationField = By.xpath(".//input[@placeholder ='* Станция метро']");
    //поле Телефон
    private By phoneField = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    //поле Дата
    private By dateField = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");
    //поле срок аренды
    private By rentTimeField = By.xpath(".//div[@class ='Dropdown-root']");
    //поля с чекбоксами
    private By checkboxBlack = By.xpath(".//input[@id ='black']");
    private By checkboxGrey = By.xpath(".//input[@id ='grey']");
    //поле комментария
    private By commentField = By.xpath(".//div/input[@placeholder ='Комментарий для курьера']");
    //кнопка Заказать после заполнения всех полей
    private By orderButtonFinal = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[position()=2]");

    //кнопка Да
    private By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div/button[text() = 'Да']");

    //выпадающее окно станций
    private By stationList = By.xpath(".//div[@class = 'Order_Text__2broi']");
    //клик по первому элементу списка станций
    private By firstElementModal = By.xpath(".//div[@class = 'select-search__select']/ul/li[position() = 1]");

    //первое модальное окно после заказа
    private By orderModalConfirmOrder = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Хотите оформить заказ?']");

    //второе модальное окно после заказа
    private By orderModalSuccessfullOrder = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Заказ оформлен']");


    public Orderpage(WebDriver driver) {
        this.driver = driver;
    }

    //клик по выпадающему элементу
    public void clickLastElementMetroStationList() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(stationList));
        driver.findElement(firstElementModal).click();
    }

    //заполняем метро
    public void setStation(String station) {
        driver.findElement(stationField).sendKeys(station);
    }

    //заполняем имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //заполняем фамилию
    public void setSecondName(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    //заполняем Адрес
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //заполняем Телефон
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //ждём появления поля даты
    public void setDate(String date) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(dateField));
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }

    //заполняем срок аренды, ждём индекс элемента
    public void setRentTime(int rentIndex) {
        driver.findElement(rentTimeField).click();
        driver.findElement(By.xpath(".//div[@class ='Dropdown-menu']/div[position()=" + rentIndex + "]")).click();

    }

    //клик по чекбоксам
    public void clickCheckboxBlack(Boolean isActive) {
        if (isActive) {
            driver.findElement(checkboxBlack).click();
        }
    }

    public void clickCheckboxGrey(Boolean isActive) {
        if (isActive) {
            driver.findElement(checkboxGrey).click();
        }
    }

    //заполняем коммент
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //кликаем по кнопке "Далее"
    public void clickNextButton() {
        //assertTrue(driver.findElement(nextButton).isEnabled());
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    //ждём прогрузку страницы
    public void waitForLoadOrderContent() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderContent));
    }

    //клик по кнопке Заказать после заполнения полей
    public void clickOrderButtonFinal() {
        driver.findElement(orderButtonFinal).click();
    }

    public void clickYesButtonFinal() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalConfirmOrder));
        driver.findElement(yesButton).click();
    }

    public void waitOrderModalSecond() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalSuccessfullOrder));
    }


}
