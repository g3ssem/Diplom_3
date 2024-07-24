import api.User;
import api.UserClient;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.ForgotPassPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.time.Duration;

import static api.UserClient.BASE_URL;
import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    public WebDriver driver;
    UserClient userClient = new UserClient();
    private String emailTest;
    private String passwordTest;
    private String nameTest;
    private String accessToken;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        RestAssured.baseURI = BASE_URL;
        Faker faker = new Faker();
        emailTest = faker.letterify("??????????@example.ru");
        passwordTest = faker.numerify("######");
        nameTest = faker.name().firstName();
        User user = new User(emailTest, passwordTest, nameTest);
        Response response = userClient.userCreate(user);
        accessToken = response.path("accessToken");

    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в акаунт' на главной")
    public void loginOnMainPageWithLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(emailTest, passwordTest);
        assertTrue("Нет кнопки оформить заказ, авторизация неудачая", mainPage.checkOrderButton());

    }
    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' на главной")
    public void loginOnMainPageWithMyAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickMyAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(emailTest, passwordTest);
        assertTrue("Нет кнопки оформить заказ, авторизация неудачая", mainPage.checkOrderButton());

    }

    @Test
    @DisplayName("Вход через страницу регистрации")
    public void loginOnRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickMyAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
        loginPage.userLogin(emailTest, passwordTest);
        assertTrue("Нет кнопки оформить заказ, авторизация неудачая", mainPage.checkOrderButton());

    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    public void loginOnForgotPassPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickMyAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickPassForgotButton();
        ForgotPassPage forgotPassPage = new ForgotPassPage(driver);
        forgotPassPage.clickLoginButton();
        loginPage.userLogin(emailTest, passwordTest);
        assertTrue("Нет кнопки оформить заказ, авторизация неудачая", mainPage.checkOrderButton());

    }

    @After
    public void tearDown() {

        driver.quit();

        if (accessToken != null) {
            userClient.userDelete(accessToken);

        }
    }
}
