package de.mandyblaschke.requirefortesting.models;

import java.util.Objects;

public class User {
    private int id;
    private String role;
    private String mail;

    public User(int id, String role, String mail) {
        this.id = id;
        this.role = role;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
