package mail.sent;

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

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/form/div[1]/div[1]/span[1]/a")
    private WebElement newMailButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/form/div[2]/div/table/tbody/tr[1]/td[2]/div/div/input")
    private WebElement receiverInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/form/div[2]/div/table/tbody/tr[4]/td[2]/div/div/input")
    private WebElement titleInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/form/div[2]/div/textarea")
    private WebElement bodyInput;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div/input[2]")
    private WebElement sendButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div/div[1]/span[2]")
    private WebElement openSentFolder;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/form/div[3]/div")
    private WebElement messageList;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[3]/a[2]")
    private WebElement logoutButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/span/a")
    private WebElement sentReceiverInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div/span")
    private WebElement sentTitleInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[2]/div[2]/div/p")
    private WebElement sentBodyInput;

    private final String messagePath = "//*[@class='b-messages__message' or @class='b-messages__message b-messages__message_last']";

    private final String messageSubjectPath = "//*[@class='b-messages__subject']";

    private final String messageNamePath = "//*[@class='b-messages__from__text']";

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

        List<WebElement> messages = messageList.findElements(By.xpath(messagePath));

        for (WebElement e : messages) {
            var subject = e.findElement(By.xpath(messageSubjectPath));

            if (subject.getText().equals(testMessage.title)) {
                foundElement = e.findElement(By.xpath(messageNamePath));
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
