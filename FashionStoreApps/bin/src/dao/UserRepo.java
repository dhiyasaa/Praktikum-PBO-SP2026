package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Database;
import model.User;

public class UserRepo implements UserDAO {

    Connection conn;

    public UserRepo() {
        conn = Database.koneksi();
    }

    @Override
    public User login(String username, String password) {

        User user = null;

        try {

            String sql = "SELECT * FROM user WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return user;

    }

}