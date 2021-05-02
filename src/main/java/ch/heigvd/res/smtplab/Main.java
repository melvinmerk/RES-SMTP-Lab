package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.config.ConfigManager;
import ch.heigvd.res.smtplab.model.mail.Person;
import ch.heigvd.res.smtplab.model.prank.Prank;
import ch.heigvd.res.smtplab.model.prank.PrankGenerator;
import ch.heigvd.res.smtplab.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        // Get configurations
        ConfigManager configManager = new ConfigManager();
        List<String> messages = configManager.loadMessageFromFile("config/messages.utf8");
        List<Person> victims = configManager.loadVictimsFromFile("config/victims.utf8");

        // generate pranks
        PrankGenerator prankGenerator = new PrankGenerator(configManager.getNumberOfGroups(), messages, victims, configManager.getWitness());
        List<Prank> pranks = prankGenerator.genPranks();

        // Send all the pranks
        SmtpClient smtpClient = new SmtpClient(configManager.getSmptServerAddress(), configManager.getSmtpServerPort());
        for(Prank prank : pranks) {
            smtpClient.sendMail(prank.generateMail());
        }
    }
}
