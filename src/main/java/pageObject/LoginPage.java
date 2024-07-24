package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private By enterHeader = By.xpath(".//h2[text()='Вход']");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//input[contains(@class, 'text input__textfield')and @type='password']");
    private By loginButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and text()='Войти']");
    private By registrationButton = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']");
    private By passForgotButton = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj') and text()='Восстановить пароль']");
    public LoginPage userLogin (String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }
    public LoginPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return this;
    }
    public LoginPage clickPassForgotButton() {
        driver.findElement(passForgotButton).click();
        return this;
    }
    public boolean checkEnterHeader() {
        return driver.findElements(enterHeader).size() > 0;
    }


}
