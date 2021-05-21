package mail;

public class Properties {

    public static final String username = "//*[@class='Textinput-Control' and @name='login']";
    public static final String signInButton = "//*[@class='passp-button passp-sign-in-button']";
    public static final String password = "//*[@id=\"passp-field-passwd\"]";
    public static final String loginButton = "//*[@class='passp-button passp-sign-in-button']";

    public static final String newMailButton = "//*[@aria-label='Написать']";
    public static final String receiverInput = "//*[@class='b-form-input__input' and @name='to']";
    public static final String titleInput = "//*[@class='b-form-input__input' and @name='subj']";
    public static final String bodyInput = "//*[@id='compose-send']";
    public static final String saveToDraftsButton = "//*[@name='nosend']";
    public static final String openDrafts = "//*[@aria-label='Черновики, нет новых писем']";
    public static final String messageList = "//*[@class='b-messages']";
    public static final String message = "//*[@class='b-messages__message' or @class='b-messages__message b-messages__message_last']";
    public static final String messageSubject = "//*[@class='b-messages__subject']";
    public static final String messageName = "//*[@class='b-messages__from__text']";
    public static final String logoutButton = "//*[@class='b-header__link b-header__link_exit']";

    public static final String sendButton = "//*[@name='doit']";
    public static final String openSentFolder = "//*[@aria-label='Отправленные, нет новых писем']";
    public static final String sentReceiverInput = "//*[@class='b-message-head__email']";
    public static final String sentTitleInput = "//*[@class='b-message-head__subject-text']";
    public static final String sentBodyInput = "//*[@class='b-message-body__content']";
}
