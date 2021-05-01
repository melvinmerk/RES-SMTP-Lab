package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class SmtpClient implements ISmtpClient {

    private BufferedReader reader;
    private PrintWriter writer;


    private String smptServerAddress = "localhost";
    private int smtpServerPort = 25;

    public SmtpClient(String smptServerAddress, int port) {
        this.smptServerAddress = smptServerAddress;
        this.smtpServerPort = port;
    }

    // default constructor with local test server
    public SmtpClient() {}

    @Override
    public void sendMail(Mail mail) throws IOException {

        // Test
        Socket socket = new Socket(smptServerAddress, smtpServerPort);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

        System.out.println(reader.readLine());

        writer.println("EHLO " + smptServerAddress);

        String line = reader.readLine();
        System.out.println(line);

        while(line.startsWith("250-")) {
            line = reader.readLine();
            System.out.println(line);
        }

        if(!line.startsWith("250 "))
            throw new IOException("Error");

        StringBuilder header = new StringBuilder("From: " + mail.getFrom() + "\n");
        writer.println("MAIL FROM: " + mail.getFrom());

        reader.readLine();

        header.append("To: ");
        String prefix = "";
        for(String to : mail.getTo()) {
            header.append(prefix);
            header.append(to);
            prefix = ", ";

            String msg = "RCPT TO: " + to;
            writer.println(msg);
            reader.readLine();
        }

        header.append("\n");
        header.append("Cc: ");
        prefix = "";
        for(String toCC : mail.getToCC()) {
            header.append(prefix);
            header.append(toCC);
            prefix = ", ";

            String msg = "RCPT TO: " + toCC;
            writer.println(msg);
            reader.readLine();
        }

        header.append("\n");

        writer.println("DATA");

        reader.readLine();

        header.append(mail.getBody());

        writer.println(header.toString());
        writer.println(".");

        reader.readLine();

        writer.println("QUIT");

        socket.close();

    }
}
