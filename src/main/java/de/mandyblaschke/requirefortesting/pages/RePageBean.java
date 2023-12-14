package de.mandyblaschke.requirefortesting.pages;

import de.mandyblaschke.requirefortesting.database.AnforderungenDbBean;
import de.mandyblaschke.requirefortesting.global.AuthorizeBean;
import de.mandyblaschke.requirefortesting.models.Anforderung;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RePageBean implements Serializable {

    @Inject
    private AnforderungenDbBean anforderungenDbBean;

    private String view;
    private List<Anforderung> listAnforderungen;

    private String nameInput;
    private String inhaltInput;

    public void gotoList() {
        view = "list";
        listAnforderungen = anforderungenDbBean.getAnforderungen();
    }

    public void gotoAdd() {
        view = "add";
        nameInput = "";
        inhaltInput = "";
    }

    public void add() {
        anforderungenDbBean.addAnforderung(nameInput, inhaltInput);
        gotoList();
    }

    public String getView() {
        return view;
    }

    public List<Anforderung> getListAnforderungen() {
        return listAnforderungen;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public String getInhaltInput() {
        return inhaltInput;
    }

    public void setInhaltInput(String inhaltInput) {
        this.inhaltInput = inhaltInput;
    }
}
