package mail.common;

import mail.Properties;
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

    @FindBy(xpath = Properties.username)
    private WebElement username;

    @FindBy(xpath = Properties.signInButton)
    private WebElement signInButton;

    @FindBy(xpath = Properties.password)
    private WebElement password;

    @FindBy(xpath = Properties.loginButton)
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
