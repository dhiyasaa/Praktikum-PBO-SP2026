package DAO;

import confg.Database;
import DAO.CostumerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Costumer;

public class CostumerRepo implements CostumerDAO {

    private Connection connection;

    final String insert =
            "INSERT INTO costumer(nama,alamat,no_hp) VALUES(?,?,?)";

    final String select =
            "SELECT * FROM costumer";

    final String update =
            "UPDATE costumer SET nama=?, alamat=?, no_hp=? WHERE id=?";

    final String delete =
            "DELETE FROM costumer WHERE id=?";

    public CostumerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Costumer costumer) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(insert);

            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public List<Costumer> show() {

        List<Costumer> ls = null;

        try {

            ls = new ArrayList<>();

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {

                Costumer c = new Costumer();

                c.setId(rs.getString("id"));
                c.setNama(rs.getString("nama"));
                c.setAlamat(rs.getString("alamat"));
                c.setNoHp(rs.getString("no_hp"));

                ls.add(c);

            }

        } catch (SQLException e) {

            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, null, e);

        }

        return ls;

    }

    @Override
    public void update(Costumer costumer) {

        PreparedStatement st = null;

        try {

            st = connection.prepareStatement(update);

            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());
            st.setString(4, costumer.getId());

            st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                if (st != null)
                    st.close();
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
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

}