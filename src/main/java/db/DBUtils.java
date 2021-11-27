package db;

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
    //    private final DataSource ds;
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
            ds = (DataSource) envContext.lookup("jdbc/TourAgency");
        } catch (NamingException ex) {
            ds = null;
//            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public Connection getConnection() throws SQLException {
        if (ds == null) {
            String DB_URL = "jdbc:mysql://localhost:3306/tour-agency";
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

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                LOG.log(Level.WARNING, "Cannot rollback DB!:" + e.getMessage());
            }
        }
    }

    public static void setAutocommit(Connection con, boolean flag) {
        try {
            con.setAutoCommit(flag);
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Cannot autocommit DB!:" + e.getMessage());
        }
    }
}
