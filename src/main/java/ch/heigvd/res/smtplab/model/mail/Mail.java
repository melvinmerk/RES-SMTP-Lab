package ch.heigvd.res.smtplab.model.mail;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Mail {

    private String from;
    private String[] to;
    private String[] toBCC;
    private String body;

    public Mail(String from, String body, String[] to, String[] toBCC) {
        this.from = from;
        this.body = encodeBodySubject(body);
        this.to = to;
        this.toBCC = toBCC;
    }

    private String encodeBodySubject(String body) {

        StringBuilder stringBuilder = new StringBuilder();

        String[] lines = body.split("\\r?\\n");

        if(!lines[0].startsWith("Subject: "))
            throw new RuntimeException("Malformed message, has to start with \"Subject: \"");

        String subject = lines[0].substring(9);
        subject = "=?utf-8?B?" +
                Base64.getEncoder().encodeToString(subject.getBytes(StandardCharsets.UTF_8)) + "?=";

        // Subject base 64 encoded
        stringBuilder.append("Subject: ").append(subject).append("\r\n");

        for(int i = 1; i < lines.length; i++) {
            stringBuilder.append(lines[i]).append("\r\n");
        }
        return stringBuilder.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getToBCC() {
        return toBCC;
    }

    public void setToCC(String[] toBCC) {
        this.toBCC = toBCC;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
