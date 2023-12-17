package de.mandyblaschke.requirefortesting.models;

public class TestfallErgebnis {
    private int testfallId;
    private String testfallBeschreibung;
    private String testlaufName;
    private String testfallErgebnis;

    public TestfallErgebnis(int testfallId, String testfallBeschreibung, String testlaufName, String testfallErgebnis) {
        this.testfallId = testfallId;
        this.testfallBeschreibung = testfallBeschreibung;
        this.testlaufName = testlaufName;
        this.testfallErgebnis = testfallErgebnis;
    }

    public int getTestfallId() {
        return testfallId;
    }

    public void setTestfallId(int testfallId) {
        this.testfallId = testfallId;
    }

    public String getTestfallBeschreibung() {
        return testfallBeschreibung;
    }

    public void setTestfallBeschreibung(String testfallBeschreibung) {
        this.testfallBeschreibung = testfallBeschreibung;
    }

    public String getTestlaufName() {
        return testlaufName;
    }

    public void setTestlaufName(String testlaufName) {
        this.testlaufName = testlaufName;
    }

    public String getTestfallErgebnis() {
        return testfallErgebnis;
    }

    public void setTestfallErgebnis(String testfallErgebnis) {
        this.testfallErgebnis = testfallErgebnis;
    }
}
