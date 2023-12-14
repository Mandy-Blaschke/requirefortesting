package de.mandyblaschke.requirefortesting.models;


import java.util.List;

public class Testlauf {
    private int id;
    private String name;
    private int testerUserId;
    private String testerUserMail;

    private List<Integer> testfaelleIds;

    public Testlauf(int id, String name, int testerUserId, String testerUserMail, List<Integer> testfaelleIds) {
        this.id = id;
        this.name = name;
        this.testerUserId = testerUserId;
        this.testerUserMail = testerUserMail;
        this.testfaelleIds = testfaelleIds;
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

    public int getTesterUserId() {
        return testerUserId;
    }

    public void setTesterUserId(int testerUserId) {
        this.testerUserId = testerUserId;
    }

    public String getTesterUserMail() {
        return testerUserMail;
    }

    public void setTesterUserMail(String testerUserMail) {
        this.testerUserMail = testerUserMail;
    }

    public List<Integer> getTestfaelleIds() {
        return testfaelleIds;
    }

    public void setTestfaelleIds(List<Integer> testfaelleIds) {
        this.testfaelleIds = testfaelleIds;
    }
}
