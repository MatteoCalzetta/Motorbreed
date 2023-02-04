package com.example.motorbreedfinal.model.users;

public abstract class Account extends AccountSubject{
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String idAccount;

    protected String email;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public String getEmail() {
        return email;
    }

    public void changeCredentials(String credential, String newCredential){
        switch(credential){
            case "email":
                this.email = newCredential;
                break;
            case "firstname":
                this.firstName = newCredential;
                break;
            case "lastname":
                this.lastName = newCredential;
                break;
            default:
                break;
        }
        notifyObservers();
    }

}
