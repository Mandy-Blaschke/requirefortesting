package de.mandyblaschke.requirefortesting.pages;

import de.mandyblaschke.requirefortesting.database.TestfaelleDbBean;
import de.mandyblaschke.requirefortesting.database.TestlaeufeDbBean;
import de.mandyblaschke.requirefortesting.database.UserDbBean;
import de.mandyblaschke.requirefortesting.models.Testfall;
import de.mandyblaschke.requirefortesting.models.Testlauf;
import de.mandyblaschke.requirefortesting.models.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TmPageBean implements Serializable {

    @Inject
    private UserDbBean userDbBean;
    @Inject
    private TestlaeufeDbBean testlaeufeDbBean;
    @Inject
    private TestfaelleDbBean testfaelleDbBean;

    private String view;
    private List<Testfall> listTestfall;
    private List<Testlauf> listTestlauf;
    private List<User> listTesterUser;

    private String nameInput;
    private int testerUserIdInput;

    private List<Integer> selectedTestfaelleIds;

    public void gotoList() {
        view = "list";
        listTestlauf = testlaeufeDbBean.getTestlaeufe();
    }

    public void gotoAdd() {
        view = "add";
        nameInput = "";
        testerUserIdInput = 0;
        selectedTestfaelleIds = new ArrayList<>();

        listTestfall = testfaelleDbBean.getTestfaelle();

        listTesterUser = userDbBean.getTester();
    }

    public void add() {
        testlaeufeDbBean.addTestlauf(nameInput, testerUserIdInput, selectedTestfaelleIds);
        gotoList();
    }

    public String getView() {
        return view;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public int getTesterUserIdInput() {
        return testerUserIdInput;
    }

    public void setTesterUserIdInput(int testerUserIdInput) {
        this.testerUserIdInput = testerUserIdInput;
    }

    public List<Testfall> getListTestfall() {
        return listTestfall;
    }

    public List<Testlauf> getListTestlauf() {
        return listTestlauf;
    }

    public List<User> getListTesterUser() {
        return listTesterUser;
    }

    public List<Integer> getSelectedTestfaelleIds() {
        return selectedTestfaelleIds;
    }

    public void setSelectedTestfaelleIds(List<Integer> selectedTestfaelleIds) {
        this.selectedTestfaelleIds = selectedTestfaelleIds;
    }
}
