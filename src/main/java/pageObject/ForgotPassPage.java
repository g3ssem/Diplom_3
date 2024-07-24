package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPassPage {
    private final WebDriver driver;
    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
    }
    private By loginButton = By.xpath(".//a[text()='Войти']");

    public ForgotPassPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }
}
