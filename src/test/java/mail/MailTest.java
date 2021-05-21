package mail;

import io.github.bonigarcia.wdm.WebDriverManager;
import mail.common.LoginPage;
import mail.common.Message;
import mail.draft.HomePageDraft;
import mail.sent.HomePageSent;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MailTest {

    public static Logger logger;

    public static WebDriver driver;

    @BeforeAll
    static void setUp() {

        logger = LoggerFactory.getLogger(MailTest.class);

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://mail.yandex.ru/lite");
    }

    @AfterEach
    public void end() {

        //driver.quit();
    }

    @Test
    @DisplayName("Тестирование повидения папки `черновика` почтового ящика.")
    public void testMailDrafts() {

        Message testMessage = new Message("Person", "DefaultTitle", "DefaultBody");

        // Страница логирования.
        LoginPage loginPage = new LoginPage(driver);

        Assertions.assertDoesNotThrow(() -> {
            // Логирования пользователя.
            loginPage.loginValidUser();
        }, "Исключения при попытки логирования.");

        // Основная страница почты. Создания и сохранения нового письма для черновика.
        HomePageDraft homePage = new HomePageDraft(driver, testMessage);

        Assertions.assertDoesNotThrow(() -> {
            homePage.saveDraftMessage();
        }, "Исключения при попытки логирования.");

        // Запрос письма из черновика.
        WebElement message = homePage.getDraftMessage();

        // Проверка если письмо сохранилось.
        Assertions.assertNotNull(message, "Сохранёное письмо было не наидено.");

        Assertions.assertDoesNotThrow(() -> {
            // Валидация письма.
            homePage.validateDraftMessage(message);
        }, "Исключения при попытки Валидации сохранившихся письма.");

        Assertions.assertDoesNotThrow(() -> {
            // Выход из почты.
            homePage.logout();
        }, "Исключения при попытки Валидации сохранившихся письма.");
    }

    @Test
    @DisplayName("Тестирование повидения папки `Отправленные` почтового ящика.")
    public void testMailSent() {

        Message testMessage = new Message("fedorenkoelias@yandex.ru", "DefaultTitle", "DefaultBody");

        // Страница логирования.
        LoginPage loginPage = new LoginPage(driver);

        Assertions.assertDoesNotThrow(() -> {
            // Логирования пользователя.
            loginPage.loginValidUser();
        }, "Исключения при попытки логирования.");

        // Основная страница почты.
        HomePageSent homePageSent = new HomePageSent(driver, testMessage);

        Assertions.assertDoesNotThrow(() -> {
            // Создания нового письма и отправка назначенному адресу.
            homePageSent.sentMessage();
        }, "Исключения при попытки создания нового письма и отправка назначенному адресу.");

        WebElement foundMessage = homePageSent.getSentMessage();

        // Проверка если письмо отправилось.
        Assertions.assertNotNull(foundMessage);

        Assertions.assertDoesNotThrow(() -> {
            // Валидация письма.
            homePageSent.validateSentMessage(foundMessage);
        }, "Исключения при попытки создания нового письма и отправка назначенному адресу.");

        // Выход из почты.
        Assertions.assertDoesNotThrow(() -> {
            homePageSent.logout();
        }, "Исключения при выхода из почты.");
    }
}
