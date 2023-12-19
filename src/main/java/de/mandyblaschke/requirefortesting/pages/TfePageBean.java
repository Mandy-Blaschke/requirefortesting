package de.mandyblaschke.requirefortesting.pages;

import de.mandyblaschke.requirefortesting.database.AnforderungenDbBean;
import de.mandyblaschke.requirefortesting.database.TestfaelleDbBean;
import de.mandyblaschke.requirefortesting.models.Anforderung;
import de.mandyblaschke.requirefortesting.models.Testfall;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TfePageBean implements Serializable {

    @Inject
    private AnforderungenDbBean anforderungenDbBean;
    @Inject
    private TestfaelleDbBean testfaelleDbBean;

    private String view;
    private List<Testfall> listTestfall;
    private List<Anforderung> listAnforderung;

    private String beschreibungInput;
    private int anforderungIdInput;

    public void gotoList() {
        view = "list";
        listTestfall = testfaelleDbBean.getTestfaelle();
    }

    public void gotoAdd() {
        view = "add";
        beschreibungInput = "";
        anforderungIdInput = 0;

        listAnforderung = anforderungenDbBean.getAnforderungen();
    }

    public void add() {
        testfaelleDbBean.addTestfall(beschreibungInput, anforderungIdInput);
        gotoList();
    }

    public String getView() {
        return view;
    }

    public List<Testfall> getListTestfall() {
        return listTestfall;
    }

    public String getBeschreibungInput() {
        return beschreibungInput;
    }

    public void setBeschreibungInput(String beschreibungInput) {
        this.beschreibungInput = beschreibungInput;
    }

    public int getAnforderungIdInput() {
        return anforderungIdInput;
    }

    public void setAnforderungIdInput(int anforderungIdInput) {
        this.anforderungIdInput = anforderungIdInput;
    }

    public List<Anforderung> getListAnforderung() {
        return listAnforderung;
    }
}
