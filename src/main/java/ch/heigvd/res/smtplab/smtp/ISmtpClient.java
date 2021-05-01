package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.IOException;

public interface ISmtpClient {

    void sendMail(Mail mail) throws IOException;
}
