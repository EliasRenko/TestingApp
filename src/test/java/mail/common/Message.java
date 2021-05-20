package mail.common;

public class Message {

    public String receiver = "fedorenkoelias@yandex.ru";
    public String title = "Default";
    public String body = "Test";

    public Message(String receiver, String title, String body) {

        this.receiver = receiver;
        this.title = title;
        this.body = body;
    }
}
