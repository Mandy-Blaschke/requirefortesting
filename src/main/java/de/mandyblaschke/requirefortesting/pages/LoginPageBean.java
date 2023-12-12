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
            if (authorizeBean.getRole().equals("re")) {
                return "re.xhtml?faces-redirect=true";
            }
        } else {
            errorMessage = "User nicht gefunden.";
        }
        return null;
    }
}
