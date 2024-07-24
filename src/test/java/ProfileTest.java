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
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;

import java.time.Duration;

import static api.UserClient.BASE_URL;
import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
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
@DisplayName("Переход по клику на 'личный кабинет'")
public void transitionInMyAccountTest () {
    MainPage mainPage = new MainPage(driver);
    mainPage.openMain().clickLoginButton();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.userLogin(emailTest, passwordTest);
    mainPage.clickMyAccount();
    ProfilePage profilePage = new ProfilePage(driver);
    assertTrue("Личный кабинет не отобразился", profilePage.checkProfileHeader());
}
    @Test
    @DisplayName("Переход по клику на 'Конструктор'")
    public void transitionInConstructorTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(emailTest, passwordTest);
        mainPage.clickMyAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();
        assertTrue("Переход в конструктор не выполнен", mainPage.checkOrderButton());
    }
    @Test
    @DisplayName("Переход по клику на 'Логотип'")
    public void transitionInLogoTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(emailTest, passwordTest);
        mainPage.clickMyAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoButton();
        assertTrue("Переход по логотипу не выполнен", mainPage.checkOrderButton());
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void logOutTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMain().clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(emailTest, passwordTest);
        mainPage.clickMyAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogOutButton();
        assertTrue("Выход не выполнен", loginPage.checkEnterHeader());
    }
    @After
    public void tearDown() {

        driver.quit();

        if (accessToken != null) {
            userClient.userDelete(accessToken);

        }
    }
}
