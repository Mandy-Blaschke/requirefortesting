package de.mandyblaschke.requirefortesting.database;

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
                        "anforderungen.name AS anforderung " +
                        "FROM testfaelle " +
                        "INNER JOIN anforderungen " +
                        "ON anforderungen_id = anforderungen.id;")
        ) {
            ResultSet rs = ps.executeQuery();
            List<Testfall> resultList = new ArrayList<Testfall>();
            while (rs.next()) {
                resultList.add(new Testfall(rs.getInt("id"), rs.getString("beschreibung"), rs.getString("anforderung")));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void addTestfall(String beschreibung, int anforderungId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO testfaelle (beschreibung, anforderungen_id, ergebnis) VALUES (?, ?, ?)")
        ) {
            ps.setString(1, beschreibung);
            ps.setInt(2, anforderungId);
            ps.setString(3, "offen");

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }
}
