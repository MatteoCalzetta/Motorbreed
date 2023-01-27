package com.example.motorbreedfinal.view1.Fagioli;

public class RegistrationBean extends LoginBean{

    private String firstName;
    private String lastName;
    private String confirmationPassword;
    private String role;

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getConfirmationPassword() { return confirmationPassword; }

    public void setConfirmationPassword(String confirmationPassword) { this.confirmationPassword = confirmationPassword; }

    public boolean checkPasswords(){
        // scrivere controllo su password;
        return true;
    }
}
