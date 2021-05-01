package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.smtp.SmtpClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SmtpClient smtpClient = new SmtpClient();

        smtpClient.sendMail(new Mail("test.a@adad.com",
                        "Subject: éo\n" +
                        "\n" +
                        "LALALLALA",
                new String[]{"to1@adad.com", "to2@adad.com"},
                new String[]{"toCC1@adad.com"}
                ));
    }
}
