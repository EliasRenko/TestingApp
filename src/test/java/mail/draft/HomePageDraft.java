package mail.draft;

import mail.Properties;
import mail.common.LoginPage;
import mail.common.Message;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePageDraft {

    protected WebDriver driver;

    private Message testMessage;

    private Logger logger;

    @FindBy(xpath = Properties.newMailButton)
    private WebElement newMailButton;

    @FindBy(xpath = Properties.receiverInput)
    private WebElement receiverInput;

    @FindBy(xpath = Properties.titleInput)
    private WebElement titleInput;

    @FindBy(xpath = Properties.bodyInput)
    private WebElement bodyInput;

    @FindBy(xpath = Properties.saveToDraftsButton)
    private WebElement saveToDraftsButton;

    @FindBy(xpath = Properties.openDrafts)
    private WebElement openDrafts;

    @FindBy(xpath = Properties.messageList)
    private WebElement messageList;

    @FindBy(xpath = Properties.logoutButton)
    private WebElement logoutButton;

    public HomePageDraft(WebDriver driver, Message testMessage) {
        logger = LoggerFactory.getLogger(LoginPage.class);

        this.driver = driver;
        this.testMessage = testMessage;

        PageFactory.initElements(driver, this);

        logger.info("Страница инициирована успешно.");
    }

    public void saveDraftMessage() {

        newMailButton.click();
        receiverInput.sendKeys(testMessage.receiver);
        titleInput.sendKeys(testMessage.title);
        bodyInput.sendKeys(testMessage.body);
        saveToDraftsButton.click();
    }

    public WebElement getDraftMessage() {

        openDrafts.click();

        WebElement foundElement = null;

        List<WebElement> messages = messageList.findElements(By.xpath(Properties.message));

        for (WebElement e : messages) {
            var subject = e.findElement(By.xpath(Properties.messageSubject));

            if (subject.getText().equals(testMessage.title)) {
                foundElement = e.findElement(By.xpath(Properties.messageName));
                break;
            }
        }

        return foundElement;
    }

    public void validateDraftMessage(WebElement message) {

        message.click();

        Assertions.assertTrue(receiverInput.getAttribute("value").contains(testMessage.receiver));
        Assertions.assertTrue(titleInput.getAttribute("value").contains(testMessage.title));
        Assertions.assertTrue(bodyInput.getAttribute("value").contains(testMessage.body));
    }

    public void logout() {

        logoutButton.click();
    }
}
