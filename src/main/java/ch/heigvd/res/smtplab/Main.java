package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.smtp.SmtpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws IOException {
        SmtpClient smtpClient = new SmtpClient();

        System.out.println(Base64.getEncoder().encodeToString("Gagné masse".getBytes(StandardCharsets.UTF_8)));

        smtpClient.sendMail(new Mail("test.a@adad.com",
                        "Subject: Gagné masse\n" +
                        "\n" +
                        "LALALLALA ééé",
                new String[]{"to1@adad.com", "to2@adad.com"},
                new String[]{"toCC1@adad.com"}
                ));
    }
}
