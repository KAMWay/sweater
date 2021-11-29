package ua.edu.chdtu.klymchuk.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBUtils {
    private static final Logger LOG = Logger.getLogger(DBUtils.class.getName());
    private static DBUtils instance;
    private DataSource ds;

    public static synchronized DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
        }
        return instance;
    }

    private DBUtils() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/Task4");
        } catch (NamingException ex) {
            ds = null;
        }
    }

    public Connection getConnection() throws SQLException {
        if (ds == null) {
            String DB_URL = "jdbc:mysql://localhost:3306/web-task4";
            String USER = "root";
            String PASS = "root";
            return DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return ds.getConnection();
    }

    public static void close(AutoCloseable as) {
        if (as != null) {
            try {
                as.close();
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Cannot closed DB!:" + e.getMessage());
            }
        }
    }
}