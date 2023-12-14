package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.Testlauf;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ApplicationScoped
public class TestlaeufeDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;


    public List<Testlauf> getTestlaeufe() {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT " +
                        "testlaeufe.id AS tl_id, " +
                        "testlaeufe.name AS tl_name, " +
                        "user.mail AS t_mail " +
                        "FROM testlaeufe " +
                        "INNER JOIN user " +
                        "ON testlaeufe.user_id = user.id;")
        ) {
            ResultSet rs = ps.executeQuery();
            List<Testlauf> resultList = new ArrayList<Testlauf>();
            while (rs.next()) {
                int tlId = rs.getInt("tl_id");
                resultList.add(
                        new Testlauf(
                                tlId,
                                rs.getString("tl_name"),
                                rs.getString("t_mail"),
                                testfaelleBeschreibungen(tlId)
                        ));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public List<String> testfaelleBeschreibungen(int testlaufId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement(
                        "SELECT testfaelle.beschreibung AS beschreibung FROM bez_testlaeufe_testfaelle " +
                                "INNER JOIN testfaelle " +
                                "ON bez_testlaeufe_testfaelle.testfaelle_id = testfaelle.id " +
                                "WHERE bez_testlaeufe_testfaelle.testlaeufe_id = ?")
        ) {
            ps.setInt(1, testlaufId);
            ResultSet rs = ps.executeQuery();
            List<String> resultList = new ArrayList<String>();
            while (rs.next()) {
                resultList.add(rs.getString("beschreibung"));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void addTestlauf(String name, int testerUserId, List<Integer> testfaelleIds) {
        Integer testlaufId = null;

        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO testlaeufe (name, user_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, name);
            ps.setInt(2, testerUserId);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    testlaufId = rs.getInt(1);
                }
            }

        } catch (SQLException ignored) {
        }
        if (testlaufId != null) {
            for (Integer testfallId : testfaelleIds) {
                try (
                        PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO bez_testlaeufe_testfaelle (testfaelle_id, testlaeufe_id) VALUES (?, ?)")
                ) {
                    ps.setInt(1, testfallId);
                    ps.setInt(2, testlaufId);

                    ps.executeUpdate();

                } catch (SQLException ignored) {
                }
            }
        }
    }
}
