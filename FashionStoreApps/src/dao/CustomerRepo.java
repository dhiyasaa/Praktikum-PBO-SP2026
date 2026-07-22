package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.Database;
import model.Customer;
import model.CustomerBuilder;

public class CustomerRepo implements CustomerDAO {

    Connection conn;

    public CustomerRepo() {

        conn = Database.koneksi();

    }

    @Override
    public void save(Customer customer) {

        try {

            String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getIdCustomer());
            ps.setString(2, customer.getNama());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAlamat());
            ps.setString(5, customer.getNoHp());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Customer customer) {

        try {

            String sql = "UPDATE customer SET "
                    + "nama=?,"
                    + "email=?,"
                    + "alamat=?,"
                    + "no_hp=? "
                    + "WHERE id_customer=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getNama());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAlamat());
            ps.setString(4, customer.getNoHp());
            ps.setString(5, customer.getIdCustomer());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String idCustomer) {

        try {

            String sql = "DELETE FROM customer WHERE id_customer=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idCustomer);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Customer> show() {

        List<Customer> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM customer ORDER BY id_customer ASC";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Customer customer = new CustomerBuilder()

                        .setIdCustomer(rs.getString("id_customer"))
                        .setNama(rs.getString("nama"))
                        .setEmail(rs.getString("email"))
                        .setAlamat(rs.getString("alamat"))
                        .setNoHp(rs.getString("no_hp"))
                        .build();

                list.add(customer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<Customer> search(String keyword) {

        List<Customer> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM customer WHERE nama LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Customer customer = new CustomerBuilder()

                        .setIdCustomer(rs.getString("id_customer"))
                        .setNama(rs.getString("nama"))
                        .setEmail(rs.getString("email"))
                        .setAlamat(rs.getString("alamat"))
                        .setNoHp(rs.getString("no_hp"))
                        .build();

                list.add(customer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public String generateId() {

        String id = "CS001";

        try {

            String sql = "SELECT id_customer FROM customer ORDER BY id_customer DESC LIMIT 1";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                String last = rs.getString("id_customer");

                int angka = Integer.parseInt(last.substring(2));

                angka++;

                id = String.format("CS%03d", angka);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

}