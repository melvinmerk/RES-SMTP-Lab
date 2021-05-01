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
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        System.out.println(reader.readLine());

        writer.print("EHLO ");
        writer.print(smptServerAddress);
        writer.print("\r\n");
        writer.flush();

        String line = reader.readLine();
        System.out.println(line);

        while(line.startsWith("250-")) {
            line = reader.readLine();
            System.out.println(line);
        }

        if(!line.startsWith("250 "))
            throw new IOException("Error");

        // Start to send
        StringBuilder header = new StringBuilder("From: " + mail.getFrom() + "\r\n");

        writer.print("MAIL FROM: ");
        writer.print(mail.getFrom());
        writer.print("\r\n");
        writer.flush();

        reader.readLine();

        header.append("To: ");
        String prefix = "";
        for(String to : mail.getTo()) {
            header.append(prefix);
            header.append(to);
            prefix = ", ";

            writer.print("RCPT TO: ");
            writer.print(to);
            writer.print("\r\n");
            writer.flush();

            reader.readLine();
        }

        header.append("\r\n");
        header.append("Cci: ");
        prefix = "";
        for(String toBCC : mail.getToBCC()) {
            header.append(prefix);
            header.append(toBCC);
            prefix = ", ";


            writer.print("RCPT TO: ");
            writer.print(toBCC);
            writer.print("\r\n");
            writer.flush();

            reader.readLine();
        }

        header.append("\r\n");

        writer.print("DATA");
        writer.print("\r\n");
        writer.flush();

        reader.readLine();
        writer.write("Content-type: text/plain; charset=\"utf-8\"\r\n");

        header.append(mail.getBody());



        writer.print(header.toString());

        // End data "section"
        writer.print("\r\n");
        writer.print(".");
        writer.print("\r\n");

        writer.flush();

        reader.readLine();

        // Quit
        writer.print("QUIT");
        writer.print("\r\n");
        writer.flush();

        socket.close();

    }
}
