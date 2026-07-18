package DAO;

import confg.Database;
import DAO.OrderDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetail;

public class OrderDetailRepo implements OrderDetailDAO {

    private Connection connection;

    final String insert =
            "INSERT INTO order_detail (order_id, service_id, harga, jumlah, total) VALUES (?,?,?,?,?)";

    final String select =
            "SELECT * FROM order_detail WHERE order_id='";

    final String delete =
            "DELETE FROM order_detail WHERE id=?";

    final String update =
            "UPDATE order_detail SET order_id=?, service_id=?, harga=?, jumlah=?, total=? WHERE id=?";

    public OrderDetailRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(OrderDetail od) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(insert);

            st.setString(1, od.getOrder_id());
            st.setString(2, od.getService_id());
            st.setString(3, od.getHarga());
            st.setString(4, od.getJumlah());
            st.setString(5, od.getTotal());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public List<OrderDetail> show(String order_id) {

        List<OrderDetail> ls = null;

        try {

            ls = new ArrayList<>();

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(select + order_id + "'");

            while (rs.next()) {

                OrderDetail od = new OrderDetail();

                od.setId(rs.getString("id"));
                od.setOrder_id(rs.getString("order_id"));
                od.setService_id(rs.getString("service_id"));
                od.setHarga(rs.getString("harga"));
                od.setJumlah(rs.getString("jumlah"));
                od.setTotal(rs.getString("total"));

                ls.add(od);

            }

        } catch (SQLException e) {

            Logger.getLogger(OrderDetailRepo.class.getName())
                    .log(Level.SEVERE, null, e);

        }

        return ls;

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

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void update(OrderDetail od) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(update);

            st.setString(1, od.getOrder_id());
            st.setString(2, od.getService_id());
            st.setString(3, od.getHarga());
            st.setString(4, od.getJumlah());
            st.setString(5, od.getTotal());
            st.setString(6, od.getId());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String total(String order_id) {

        String result = "0";

        String query =
                "SELECT SUM(total) AS total FROM order_detail WHERE order_id='" + order_id + "'";

        try {

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                result = rs.getString(1);

                if (result == null) {
                    result = "0";
                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return result;

    }

}