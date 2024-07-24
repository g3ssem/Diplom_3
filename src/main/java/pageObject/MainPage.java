package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static api.UserClient.BASE_URL;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By myAccount = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By bunButton = By.xpath(".//span[text()='Булки']/parent::div");
    private By sauceButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private By fillingButton = By.xpath(".//span[text()='Начинки']/parent::div");
    private By bunSeletcted = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[text()='Булки']");
    private By sauceSeletcted = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[text()='Соусы']");
    private By fillingSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[text()='Начинки']");
    private By orderButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg')]");

    public MainPage openMain() {
        driver.get(BASE_URL);
        return this;
    }
    public MainPage clickMyAccount() {
        driver.findElement(myAccount).click();
        return this;
    }


    public MainPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public MainPage clickBunSection() {
        driver.findElement(bunButton).click();
        return this;
    }

    public MainPage clickSauceSection() {
        driver.findElement(sauceButton).click();
        return this;
    }

    public MainPage clickFillingSection() {
        driver.findElement(fillingButton).click();
        return this;
    }

    public boolean checkBunSelected() {
        return driver.findElements(bunSeletcted).size() > 0;
    }

    public boolean checkSauceSelected() {
        return driver.findElements(sauceSeletcted).size() > 0;
    }

    public boolean checkFillingSelected() {
        return driver.findElements(fillingSelected).size() > 0;
    }

    public boolean checkOrderButton() {
        return driver.findElements(orderButton).size() > 0;
    }
}
