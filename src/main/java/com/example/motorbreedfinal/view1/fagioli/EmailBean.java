package com.example.motorbreedfinal.view1.fagioli;

public class EmailBean {
    private String fromEmail;
    private String toEmail;
    private String description;
    private String password;

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validation(){
        return (fromEmail.length()>6 && fromEmail.contains("@") && fromEmail.contains("."));
    }
}
