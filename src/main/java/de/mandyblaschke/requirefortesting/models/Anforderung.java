package de.mandyblaschke.requirefortesting.models;


public class Anforderung {
    private int id;
    private String name;
    private String inhalt;

    public Anforderung(int id, String name, String inhalt) {
        this.id = id;
        this.name = name;
        this.inhalt = inhalt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
}
