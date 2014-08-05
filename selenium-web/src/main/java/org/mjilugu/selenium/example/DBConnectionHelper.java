package org.mjilugu.selenium.example;

import com.sybase.jdbc3.jdbc.SybConnection;
import com.sybase.jdbc3.jdbc.SybDriver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DBConnectionHelper {

    private static DBConnectionHelper instance;
    private SybConnection connection;
    private SybDriver driver;
    
    public Logger logger = Logger.getLogger(DBConnectionHelper.class.getName());
    final String DRIVER_NAME = "com.sybase.jdbc3.jdbc.SybDriver";

    public static DBConnectionHelper getInstance() {
        if (instance == null) {
            instance = new DBConnectionHelper();
            try {
                instance.init();
            } catch (final SQLException e) {
                e.printStackTrace();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    void init() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        driver = (SybDriver) Class.forName(DRIVER_NAME).newInstance();
        DriverManager.registerDriver(driver);

        connection = (SybConnection) DriverManager.getConnection("jdbc:sybase:Tds:"
                + "atrcxb1842.athtem.eei.ericsson.se:2640", "dc", "dc");
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
            instance = null;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public void commit() throws SQLException {
        connection.commit();
    }
}
