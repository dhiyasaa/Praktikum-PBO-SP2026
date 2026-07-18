package DAO;

import confg.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserRepo implements UserDAO {

    private Connection connection;

    final String insert = "INSERT INTO user (name, username, password) VALUES (?,?,?)";
    final String select = "SELECT * FROM user";
    final String delete = "DELETE FROM user WHERE id=?";
    final String update = "UPDATE user SET name=?, username=?, password=? WHERE id=?";
    final String login = "SELECT * FROM user WHERE username=? AND password=?";

    public UserRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(User user) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(insert);

            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public List<User> show() {

        List<User> ls = new ArrayList<>();

        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getString("id"));
                user.setNama(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                ls.add(user);

            }

        } catch (SQLException e) {

            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, e);

        }

        return ls;

    }

    @Override
    public void update(User user) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(update);

            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getId());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void delete(String id) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(delete);

            st.setString(1, id);

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean login(String username, String password) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = connection.prepareStatement(login);

            st.setString(1, username);
            st.setString(2, password);

            rs = st.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

}