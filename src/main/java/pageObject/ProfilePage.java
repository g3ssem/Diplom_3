package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    private By profileLink = By.xpath(".//a[text()='Профиль']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private By logOutButton = By.xpath(".//button[text()='Выход']");

    public boolean checkProfileHeader() {
        return driver.findElements(profileLink).size() > 0;
    }

    public ProfilePage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    public ProfilePage clickLogoButton() {
        driver.findElement(logoButton).click();
        return this;
    }

    public ProfilePage clickLogOutButton() {
        driver.findElement(logOutButton).click();
        return this;
    }
}
