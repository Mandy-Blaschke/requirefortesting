package de.mandyblaschke.requirefortesting.models;


public class Testfall {
    private int id;
    private String beschreibung;
    private String anforderung;

    public Testfall(int id, String beschreibung, String anforderung) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.anforderung = anforderung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getAnforderung() {
        return anforderung;
    }

    public void setAnforderung(String anforderung) {
        this.anforderung = anforderung;
    }
}
