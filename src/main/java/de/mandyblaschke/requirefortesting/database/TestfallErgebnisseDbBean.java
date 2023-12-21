package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.TestfallErgebnis;
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
public class TestfallErgebnisseDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;

    public List<TestfallErgebnis> getTestfallErgebnisForTester(int testerId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT " +
                        "testfaelle.id AS testfall_id, " +
                        "testfaelle.beschreibung AS testfall_beschreibung, " +
                        "testlaeufe.name AS testlauf_name, " +
                        "testfaelle.ergebnis AS testfall_ergebnis " +
                        "FROM testlaeufe " +
                        "INNER JOIN bez_testlaeufe_testfaelle " +
                        "ON testlaeufe.id = bez_testlaeufe_testfaelle.testlaeufe_id " +
                        "INNER JOIN testfaelle " +
                        "ON bez_testlaeufe_testfaelle.testfaelle_id = testfaelle.id " +
                        "WHERE testlaeufe.user_id = ?;")
        ) {
            ps.setInt(1, testerId);
            ResultSet rs = ps.executeQuery();
            List<TestfallErgebnis> resultList = new ArrayList<TestfallErgebnis>();
            while (rs.next()) {
                boolean bereitsVorhanden = false;
                for (TestfallErgebnis tfe: resultList) {
                    if (tfe.getTestfallId() == rs.getInt("testfall_id")){
                        bereitsVorhanden = true;
                    }
                }
                if (!bereitsVorhanden) {
                    resultList.add(new TestfallErgebnis(
                            rs.getInt("testfall_id"),
                            rs.getString("testfall_beschreibung"),
                            rs.getString("testlauf_name"),
                            rs.getString("testfall_ergebnis")
                    ));
                }
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void editTestfallErgebnis(int testfallId, String testfallErgebnis) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("UPDATE testfaelle SET ergebnis=? WHERE id = ?")
        ) {
            ps.setString(1, testfallErgebnis);
            ps.setInt(2, testfallId);

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }
}
