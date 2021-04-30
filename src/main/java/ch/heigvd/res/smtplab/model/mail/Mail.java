package ch.heigvd.res.smtplab.model.mail;


public class Mail {

    private String from;
    private String[] to = new String[0];
    private String[] toCC = new String[0];
    private String body;

    public Mail() {}

    public Mail(String from, String body, String[] to, String[] toCC) {
        this.from = from;
        this.body = body;
        this.to = to;
        this.toCC = toCC;
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

    public String[] getToCC() {
        return toCC;
    }

    public void setToCC(String[] toCC) {
        this.toCC = toCC;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
