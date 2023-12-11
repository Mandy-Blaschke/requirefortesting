package de.mandyblaschke.requirefortesting;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    // Username + Getter + Setter
    private String user = "";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // Error + Getter + Setter
    private String errorMessage = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // isAuthorized + Getter + Setter
    private boolean isAuthorized = false;

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    // Rolle + Getter + Setter

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Login Funktion
    public String login() {
        boolean loginSuccess = false;

        if (user.equals("yolo")) {
            setRole("re");
            loginSuccess = true;
        }

        if (loginSuccess) {
            errorMessage = "";
            if (role.equals("re")) {
                return "re.xhtml?faces-redirect=true";
            }
        } else {
            errorMessage = "User nicht gefunden.";
        }
        return null;
    }
}
