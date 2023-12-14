package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;

    public User getUserByMail(String mail) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT id, role, mail FROM user WHERE mail = ?;")
        ) {
            ps.setString(1, mail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("role"), rs.getString("mail"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getTester() {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT id, role, mail FROM user WHERE role = ?;")
        ) {
            ps.setString(1, "tester");

            ResultSet rs = ps.executeQuery();

            List<User> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new User(rs.getInt("id"), rs.getString("role"), rs.getString("mail")));
            }

            return result;

        } catch (SQLException e) {
            return null;
        }
    }
}
