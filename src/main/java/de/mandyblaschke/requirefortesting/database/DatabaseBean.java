package de.mandyblaschke.requirefortesting.database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Named
@ApplicationScoped
public class DatabaseBean implements Serializable {

    private Connection connection;
    public Connection getConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/requirefortesting?user=root&password=root");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
