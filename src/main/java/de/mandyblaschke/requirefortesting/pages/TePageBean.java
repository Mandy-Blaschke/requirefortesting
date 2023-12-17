package de.mandyblaschke.requirefortesting.pages;

import de.mandyblaschke.requirefortesting.database.TestfallErgebnisseDbBean;
import de.mandyblaschke.requirefortesting.global.AuthorizeBean;
import de.mandyblaschke.requirefortesting.models.Anforderung;
import de.mandyblaschke.requirefortesting.models.TestfallErgebnis;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TePageBean implements Serializable {

    @Inject
    private TestfallErgebnisseDbBean testfallErgebnisseDbBean;
    @Inject
    private AuthorizeBean authorizeBean;

    private String view;
    private List<TestfallErgebnis> listTestfallErgebnisse;

    private String ergbnisInput;
    private int testfallIdToEdit;

    public void gotoList() {
        view = "list";
        listTestfallErgebnisse = testfallErgebnisseDbBean.getTestfallErgebnisForTester(authorizeBean.getUser().getId());
    }

    public void gotoEdit(TestfallErgebnis testfallErgebnis) {
        view = "edit";
        testfallIdToEdit = testfallErgebnis.getTestfallId();
        ergbnisInput = testfallErgebnis.getTestfallErgebnis();
    }

    public void saveEditedErgebnis() {
        testfallErgebnisseDbBean.editTestfallErgebnis(testfallIdToEdit, ergbnisInput);
        gotoList();
    }

    public String getView() {
        return view;
    }

    public List<TestfallErgebnis> getListTestfallErgebnisse() {
        return listTestfallErgebnisse;
    }

    public void setListTestfallErgebnisse(List<TestfallErgebnis> listTestfallErgebnisse) {
        this.listTestfallErgebnisse = listTestfallErgebnisse;
    }

    public String getErgbnisInput() {
        return ergbnisInput;
    }

    public void setErgbnisInput(String ergbnisInput) {
        this.ergbnisInput = ergbnisInput;
    }

    public int getTestfallIdToEdit() {
        return testfallIdToEdit;
    }

    public void setTestfallIdToEdit(int testfallIdToEdit) {
        this.testfallIdToEdit = testfallIdToEdit;
    }
}
