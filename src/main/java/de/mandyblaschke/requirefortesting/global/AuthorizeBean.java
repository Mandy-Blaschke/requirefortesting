package de.mandyblaschke.requirefortesting.global;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthorizeBean implements Serializable {

    private String user = null;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isAuthorized() {
        return user != null;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean tryLogin(String user) {
        return false;
    }

}
