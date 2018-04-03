package com.example.ConspectSite.services.dto;

public class CredentialsUniqueDTO {
    private boolean emailUnique;
    private boolean usernameUnique;
    private boolean credentialsUnique;

    public boolean isEmailUnique() {
        return emailUnique;
    }

    public void setEmailUnique(boolean emailUnique) {
        this.emailUnique = emailUnique;
    }

    public boolean isUsernameUnique() {
        return usernameUnique;
    }

    public void setUsernameUnique(boolean usernameUnique) {
        this.usernameUnique = usernameUnique;
    }

    public boolean isCredentialsUnique() {
        return credentialsUnique;
    }

    public void setCredentialsUnique(boolean credentialsUnique) {
        this.credentialsUnique = credentialsUnique;
    }
}
