package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.User;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named
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
}
