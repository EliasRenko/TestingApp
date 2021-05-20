package mail.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    protected WebDriver driver;

    private Logger logger;

    final private String usernameValue = "testAutomationTask";

    final private String passwordValue = "testAutomationTask1234";

    @FindBy(xpath = "//*[@id=\"passp-field-login\"]")
    private WebElement username;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[3]/button")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[3]/button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        logger = LoggerFactory.getLogger(LoginPage.class);

        this.driver = driver;
        PageFactory.initElements(driver, this);

        logger.info("Страница инициирована успешно.");
    }

    public void loginValidUser() {

        username.sendKeys(usernameValue);
        signInButton.click();
        password.sendKeys(passwordValue);
        loginButton.click();
    }
}
