import api.UserClient;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.time.Duration;

import static api.UserClient.BASE_URL;
import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    public WebDriver driver;
    private String emailTest;
    private String passwordTest;
    private String nameTest;
    UserClient userClient = new UserClient();
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
    MainPage mainPage = new MainPage(driver);
    mainPage.openMain().clickMyAccount();

    }
    @Test
    @DisplayName("Успешная регистрашия пользователя")
    public void successRegTest () {
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        loginPage.clickRegistrationButton();
        registrationPage.userRegistration(nameTest,emailTest,passwordTest);
        assertTrue("Ошибка регистрации пользователя", loginPage.checkEnterHeader());
        String json = "{\"email\": \"" + emailTest + "\", \"password\": \"" + passwordTest + "\" }";
        Response logInUserResponse = userClient.userLogin(json);
        accessToken = logInUserResponse.path("accessToken");
        userClient.userDelete(accessToken);

    }
@Test
@DisplayName("Регистрация невозможна, при длинне пароля меньше 6ти символов")
public void errorMsgOnRegTest () {
    LoginPage loginPage = new LoginPage(driver);
    RegistrationPage registrationPage = new RegistrationPage(driver);
    loginPage.clickRegistrationButton();
    registrationPage.userRegistration(nameTest,emailTest,"12345");
    assertTrue("Сообщене о некорректной длинне пароля не появилось", registrationPage.errorMessageCheck());
}
    @After
    public void tearDown () {
       driver.quit();

    }
}
