package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

public interface ISmtpClient {

    void sendMail(Mail mail);
}
