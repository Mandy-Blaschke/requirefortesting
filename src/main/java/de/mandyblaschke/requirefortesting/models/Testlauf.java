package de.mandyblaschke.requirefortesting.models;


import java.util.List;

public class Testlauf {
    private int id;
    private String name;
    private String testerUserMail;
    private List<String> testfaelleBeschreibungen;

    public Testlauf(int id, String name, String testerUserMail, List<String> testfaelleBeschreibungen) {
        this.id = id;
        this.name = name;
        this.testerUserMail = testerUserMail;
        this.testfaelleBeschreibungen = testfaelleBeschreibungen;
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

    public String getTesterUserMail() {
        return testerUserMail;
    }

    public void setTesterUserMail(String testerUserMail) {
        this.testerUserMail = testerUserMail;
    }

    public List<String> getTestfaelleBeschreibungen() {
        return testfaelleBeschreibungen;
    }

    public void setTestfaelleBeschreibungen(List<String> testfaelleBeschreibungen) {
        this.testfaelleBeschreibungen = testfaelleBeschreibungen;
    }
}
