package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.Database;
import model.Order;
import model.OrderBuilder;

public class OrderRepo implements OrderDAO {

    Connection conn;

    public OrderRepo() {

        conn = Database.koneksi();

    }

    @Override
    public void save(Order order) {

        try {

            String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, order.getIdOrder());
            ps.setString(2, order.getTanggal());
            ps.setString(3, order.getIdCustomer());
            ps.setString(4, order.getIdProduct());
            ps.setInt(5, order.getQty());
            ps.setDouble(6, order.getTotal());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Order order) {

        try {

            String sql = "UPDATE orders SET "
                    + "tanggal=?,"
                    + "id_customer=?,"
                    + "id_product=?,"
                    + "qty=?,"
                    + "total=? "
                    + "WHERE id_order=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, order.getTanggal());
            ps.setString(2, order.getIdCustomer());
            ps.setString(3, order.getIdProduct());
            ps.setInt(4, order.getQty());
            ps.setDouble(5, order.getTotal());
            ps.setString(6, order.getIdOrder());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String idOrder) {

        try {

            String sql = "DELETE FROM orders WHERE id_order=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idOrder);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> show() {

        List<Order> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM orders";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                Order order = new OrderBuilder()

                        .setIdOrder(rs.getString("id_order"))
                        .setTanggal(rs.getString("tanggal"))
                        .setIdCustomer(rs.getString("id_customer"))
                        .setIdProduct(rs.getString("id_product"))
                        .setQty(rs.getInt("qty"))
                        .setTotal(rs.getDouble("total"))
                        .build();

                list.add(order);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public String generateId(){

        String id = "ORD001";

        try{

            String sql = "SELECT id_order FROM orders ORDER BY id_order DESC LIMIT 1";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){

                String last = rs.getString("id_order");

                int angka = Integer.parseInt(last.substring(3));

                angka++;

                id = String.format("ORD%03d", angka);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return id;

    }

}