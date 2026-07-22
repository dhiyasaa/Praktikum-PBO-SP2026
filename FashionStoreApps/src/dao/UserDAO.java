package dao;

import model.User;

public interface UserDAO {

    public User login(String username, String password);

}