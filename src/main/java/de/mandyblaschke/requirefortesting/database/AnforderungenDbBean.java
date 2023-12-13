package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.Anforderung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ApplicationScoped
public class AnforderungenDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;

    public List<Anforderung> getAnforderungen() {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT id, name, inhalt FROM anforderungen")
        ) {
            ResultSet rs = ps.executeQuery();
            List<Anforderung> resultList = new ArrayList<Anforderung>();
            while (rs.next()) {
                resultList.add(new Anforderung(rs.getInt("id"), rs.getString("name"), rs.getString("inhalt")));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void addAnforderung(String name, String inhalt) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO anforderungen (name, inhalt) VALUES (?, ?)")
        ) {
            ps.setString(1, name);
            ps.setString(2, inhalt);

            ps.executeQuery();

        } catch (SQLException ignored) {
        }
    }

    public void editAnforderung(int editId, String name, String inhalt) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("UPDATE anforderungen SET name = ?, inhalt = ? WHERE id = ?")
        ) {
            ps.setString(1, name);
            ps.setString(2, inhalt);
            ps.setInt(3, editId);

            ps.executeQuery();

        } catch (SQLException ignored) {
        }
    }
    public void removeAnforderung (int removeId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("DELETE FROM anforderungen WHERE id = ?")
        ) {
            ps.setInt(1, removeId);

            ps.executeQuery();

        } catch (SQLException ignored) {
        }
    }
}
