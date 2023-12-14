package de.mandyblaschke.requirefortesting.database;

import de.mandyblaschke.requirefortesting.models.Testlauf;
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
public class TestlaeufeDbBean implements Serializable {
    @Inject
    private DatabaseBean datebaseBean;


    public List<Testlauf> getTestlaeufe() {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT " +
                        "testlaeufe.id AS tl_id, " +
                        "testlaeufe.name AS tl_name, " +
                        "user_id AS t_id, " +
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
                                rs.getInt("t_id"),
                                rs.getString("t_mail"),
                                getTestfaelleForTestlauf(tlId)
                        ));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public List<Integer> getTestfaelleForTestlauf(int testlaufId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("SELECT testfaelle_id FROM bez_testlaeufe_testfaelle WHERE testlaeufe_id = ?")
        ) {
            ps.setInt(1, testlaufId);
            ResultSet rs = ps.executeQuery();
            List<Integer> resultList = new ArrayList<Integer>();
            while (rs.next()) {
                resultList.add(rs.getInt("testfaelle_id"));
            }
            return resultList;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void addTestlauf(String name, int testerUserId) {
        try (
                PreparedStatement ps = datebaseBean.getConnection().prepareStatement("INSERT INTO testlaeufe (name, user_id) VALUES (?, ?)")
        ) {
            ps.setString(1, name);
            ps.setInt(2, testerUserId);

            ps.executeUpdate();

        } catch (SQLException ignored) {
        }
    }
}
