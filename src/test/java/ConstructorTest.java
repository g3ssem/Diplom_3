import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;

import java.time.Duration;

import static api.UserClient.BASE_URL;
import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    public WebDriver driver;
    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        RestAssured.baseURI = BASE_URL;
    }
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void transitionOnBunsSectionTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickFillingSection().clickBunSection();
        assertTrue("Раздел булки не отображается", mainPage.checkBunSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void transitionOnSauceSectionTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickFillingSection().clickSauceSection();
        assertTrue("Раздел соусы не отображается", mainPage.checkSauceSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void transitionOnFillingSectionTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickSauceSection().clickFillingSection();
        assertTrue("Раздел начинки не отображается", mainPage.checkFillingSelected());
    }


    @After
    public void tearDown() {

        driver.quit();


        }
    }
