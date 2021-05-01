package ch.heigvd.res.smtplab.config;

import ch.heigvd.res.smtplab.model.mail.Person;

import java.util.List;

public interface IConfigManager {
    List<String> loadMessageFromFile(String filename);
    List<Person> loadVictimsFromFile(String filename);
    void loadProperties();
}
