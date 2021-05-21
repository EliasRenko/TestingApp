package mail.sent;

import mail.Properties;
import mail.common.Message;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePageSent {

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

    @FindBy(xpath = Properties.sendButton)
    private WebElement sendButton;

    @FindBy(xpath = Properties.openSentFolder)
    private WebElement openSentFolder;

    @FindBy(xpath = Properties.messageList)
    private WebElement messageList;

    @FindBy(xpath = Properties.sentReceiverInput)
    private WebElement sentReceiverInput;

    @FindBy(xpath = Properties.sentTitleInput)
    private WebElement sentTitleInput;

    @FindBy(xpath = Properties.sentBodyInput)
    private WebElement sentBodyInput;

    @FindBy(xpath = Properties.logoutButton)
    private WebElement logoutButton;

    public HomePageSent(WebDriver driver, Message testMessage) {
        logger = LoggerFactory.getLogger(HomePageSent.class);

        this.driver = driver;
        this.testMessage = testMessage;

        PageFactory.initElements(driver, this);

        logger.info("Страница инициирована успешно.");
    }

    public void sentMessage() {

        newMailButton.click();
        receiverInput.sendKeys(testMessage.receiver + ',');
        titleInput.sendKeys(testMessage.title);
        bodyInput.sendKeys(testMessage.body);
        sendButton.click();
    }

    public WebElement getSentMessage() {

        openSentFolder.click();

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

    public void validateSentMessage(WebElement message) {

        message.click();

        Assertions.assertTrue(sentReceiverInput.getText().contains(testMessage.receiver));
        Assertions.assertTrue(sentTitleInput.getText().contains(testMessage.title));
        Assertions.assertTrue(sentBodyInput.getText().contains(testMessage.body));
    }

    public void logout() throws NoSuchElementException {

        logoutButton.click();
    }
}
