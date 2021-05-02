package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.model.mail.Person;

import java.util.List;


public class Prank {

    private Person sender;
    private List<Person> recipients;
    private List<Person> copies;
    private String message;


    public Prank(Person sender, List<Person> recipients, List<Person> copies, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.copies = copies;
        this.message = message;
    }

    public Mail generateMail() {

        String[] to = recipients.stream().map(Person::getAddress).toArray(String[]::new);
        String[] toCC = copies.stream().map(Person::getAddress).toArray(String[]::new);
        return new Mail(sender.getAddress(), message, to, toCC);
    }
}
