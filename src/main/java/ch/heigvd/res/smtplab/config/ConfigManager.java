package ch.heigvd.res.smtplab.config;

import ch.heigvd.res.smtplab.model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigManager {

    private String smptServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private List<Person> witness;

    public ConfigManager() throws IOException {
        loadProperties();
    }

    public List<String> loadMessageFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8));

        ArrayList<String> messages = new ArrayList<>();
        StringBuilder message = new StringBuilder();
        String line = reader.readLine();

        while(line != null) {

            if(line.equals("===")) {
                messages.add(message.toString());
                message = new StringBuilder();
            } else {
                message.append(line).append("\r\n");
            }
            line = reader.readLine();
        }

        reader.close();

        // Ajout dernier message, pas éxecuté si le fichier est terminé avec ===
        if(message.length() > 0)
            messages.add(message.toString());

        return messages;
    }


    public List<Person> loadVictimsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8));

        ArrayList<Person> persons = new ArrayList<>();
        String line = reader.readLine();

        while(line != null) {
            persons.add(new Person(line));
            line = reader.readLine();
        }

        reader.close();

        return persons;
    }

    private void loadProperties() throws IOException {
        FileInputStream fis = new FileInputStream("config/config.properties");
        Properties prop = new Properties();
        prop.load(fis);

        this.smptServerAddress = prop.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));

        String witnessToCC = prop.getProperty("witnessToCC");
        this.witness = new ArrayList<>();

        String[] witnesses = witnessToCC.split(",");

        this.witness = Arrays.stream(witnesses).map(Person::new).collect(Collectors.toList());

        fis.close();
    }

    public String getSmptServerAddress() {
        return smptServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public List<Person> getWitness() {
        return witness;
    }
}
