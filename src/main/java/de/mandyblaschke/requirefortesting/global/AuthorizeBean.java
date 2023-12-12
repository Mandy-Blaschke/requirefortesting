package de.mandyblaschke.requirefortesting.global;

import de.mandyblaschke.requirefortesting.database.UserDbBean;
import de.mandyblaschke.requirefortesting.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthorizeBean implements Serializable {

    @Inject
    private UserDbBean userDbBean;

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAuthorized() {
        return user != null;
    }

    public boolean tryLogin(String mail) {
        User userByMail = userDbBean.getUserByMail(mail);
        if (userByMail != null) {
            setUser(userByMail);
            return true;
        } else {
            setUser(null);
            return false;
        }

    }

}
