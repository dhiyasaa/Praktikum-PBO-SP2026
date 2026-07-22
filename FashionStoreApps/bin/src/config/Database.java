package config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {

    private static Connection conn;

    public static Connection koneksi() {

        if (conn == null) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/fashion_store",
                        "root",
                        ""
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);

            }

        }

        return conn;

    }

}