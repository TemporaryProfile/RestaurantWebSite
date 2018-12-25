package restaurant.data.impl;

import restaurant.business.User;
import restaurant.data.HelperDB;
import restaurant.data.UserDao;

import java.sql.*;

public class SimpleUserDao implements UserDao {

    @Override
    public boolean emailExists(String email) {
        final String query = "SELECT Email FROM User WHERE Email = ?";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            prepStat.setString(1, email);
            try (var rs = prepStat.executeQuery()) {

                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("emailExists: " + ex);
            return false;
        }
    }

    @Override
    public boolean deleteUser(String email) {
        final String query = "DELETE FROM User WHERE Email = '" + email + "'";

        try (var con = HelperDB.getConnection();
             var stmt = con.prepareStatement(query)) {

            final int result = stmt.executeUpdate();
            return result == 0;
        } catch (SQLException ex) {
            System.err.println("deleteUser: " + ex);
            return false;
        }
    }

    @Override
    public boolean insert(User user) {
        final String query = "INSERT INTO User (FirstName, LastName, Email) VALUES (?, ?, ?)";

        try (var con = HelperDB.getConnection();
             var ps = con.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());

            ps.executeUpdate();

            //Get the InvoiceID from the last INSERT statement.
//            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
//            Statement identityStatement = con.createStatement();
//            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
//            identityResultSet.next();
//            long id = identityResultSet.getLong("IDENTITY");
//            identityResultSet.close();
//            identityStatement.close();

//            user.setId(id);
            user.setId(HelperDB.getId());
            System.out.println("userID: " + user.getId());
            return true;
        } catch (SQLException ex) {
            System.err.println("insert: " + ex);
            return false;
        }
    }

    @Override
    public User selectUser(String email) {
        final var query = "SELECT * FROM User WHERE Email = ?";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            prepStat.setString(1, email);
            try (var resSet = prepStat.executeQuery()) {
                return HelperDB.mapUser(resSet);
            }
        } catch (SQLException ex) {
            System.err.println("selectUser: " + ex);
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean subscribeUser(String email) {
        final String query = "update User set IsSubscribed = 'y' where email = ?";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            prepStat.setString(1, email);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("subscribeUser: " + ex);
        }
        return false;
    }

    @Override
    public boolean unsubscribeUser(String email) {
        final String query = "update User set IsSubscribed = 'n' where email = ?";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            prepStat.setString(1, email);
            prepStat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("unsubscribeUser: " + ex);
        }
        return false;
    }
}
