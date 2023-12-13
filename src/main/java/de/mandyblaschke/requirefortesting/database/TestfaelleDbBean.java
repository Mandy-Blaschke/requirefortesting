package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.Anforderung;
import de.mandyblaschke.requirefortesting.models.Testfall;
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
public class TestfaelleDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;



    public List<Testfall> getTestfaelle() {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT " +
                        "testfaelle.id AS id, " +
                        "beschreibung AS beschreibung, " +
                        "anforderungen.name AS anforderung, " +
                        "anforderungen.id AS anforderung_id " +
                        "FROM testfaelle " +
                        "INNER JOIN anforderungen " +
                        "ON anforderungen_id = anforderungen.id;")
        ) {
            ResultSet rs = ps.executeQuery();
            List<Testfall> resultList = new ArrayList<Testfall>();
            while (rs.next()) {
                resultList.add(new Testfall(rs.getInt("id"), rs.getString("beschreibung"), rs.getString("anforderung"), rs.getInt("anforderung_id") ));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void addTestfall(String beschreibung, int anforderungId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO testfaelle (beschreibung, anforderungen_id) VALUES (?, ?)")
        ) {
            ps.setString(1, beschreibung);
            ps.setInt(2, anforderungId);

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }

    public void editTestfall(int editId, String beschreibung, int anforderungId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("UPDATE testfaelle SET beschreibung = ?, anforderungen_id = ? WHERE id = ?")
        ) {
            ps.setString(1, beschreibung);
            ps.setInt(2, anforderungId);
            ps.setInt(3, editId);

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }
    public void removeTestfall (int removeId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("DELETE FROM testfaelle WHERE id = ?")
        ) {
            ps.setInt(1, removeId);

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }
}
