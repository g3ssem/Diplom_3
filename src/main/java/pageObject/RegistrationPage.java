package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    private By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage userRegistration (String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(regButton).click();
        return this;
    }
    public RegistrationPage clickLoginButton () {
        driver.findElement(loginButton).click();
        return this;
    }
    public boolean errorMessageCheck () {
        String text = driver.findElement(errorMessage).getText();
        if (text != null) {
            return true;
        }
        else{
            return false;
        }
    }
}
