package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnection;
import model.User;

public class LoginService {
    public boolean authenticate(User user) {
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.koneksi();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}