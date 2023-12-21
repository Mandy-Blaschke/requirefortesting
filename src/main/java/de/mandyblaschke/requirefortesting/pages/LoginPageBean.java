package de.mandyblaschke.requirefortesting.pages;

import de.mandyblaschke.requirefortesting.global.AuthorizeBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginPageBean implements Serializable {

    @Inject
    private AuthorizeBean authorizeBean;
    @Inject
    private TfePageBean tfePageBean;
    @Inject
    private TmPageBean tmPageBean;

    @Inject
    private RePageBean rePageBean;
    @Inject
    private TePageBean tePageBean;

    private String userInput = "";

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    private String errorMessage = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String loginSubmit() {
        boolean loginSuccess = authorizeBean.tryLogin(userInput);

        if (loginSuccess) {
            errorMessage = null;
            String role = authorizeBean.getUser().getRole();

            switch (role) {
                case "Requirements Engineer":
                    rePageBean.gotoList();
                    return "re.xhtml?faces-redirect=true";
                case "Testmanager":
                    tmPageBean.gotoList();
                    return "tm.xhtml?faces-redirect=true";
                case "Tester":
                    tePageBean.gotoList();
                    return "t.xhtml?faces-redirect=true";
                case "Testfallersteller":
                    tfePageBean.gotoList();
                    return "tfe.xhtml?faces-redirect=true";
            }

        } else {
            errorMessage = "User nicht gefunden.";
        }
        return null;
    }
}
