package ua.edu.chdtu.sweater.db;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager {
    private static final Logger LOG = Logger.getLogger(UserManager.class.getName());
    private static final String SQL_SELECT = "SELECT * FROM users WHERE email=? AND password=?";
    private static final String SQL_INSERT = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?);";


    protected final DBUtils connector;

    public UserManager() {
        connector = DBUtils.getInstance();
    }

    public User select(String email, int password) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection con = connector.getConnection()) {
            pstmt = con.prepareStatement(SQL_SELECT);
            pstmt.setObject(1, email);
            pstmt.setObject(2, password);

            rs = pstmt.executeQuery();
            List<User> users = new ArrayList<>();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone"));
                user.setJob(rs.getString("job"));

                return user;
            }
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return null;
    }

    public boolean insert(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection con = connector.getConnection()) {
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, user.getFullName());
            pstmt.setObject(2, user.getEmail());
            pstmt.setObject(3, user.getPhoneNumber());
            pstmt.setObject(4, user.getJob());
            pstmt.setObject(5, user.getPassword());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return false;
    }
}