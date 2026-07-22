package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.Database;
import model.Clothing;
import model.ProductBuilder;

public class ProductRepo implements ProductDAO {

    Connection conn;

    public ProductRepo() {
        conn = Database.koneksi();
    }

    @Override
    public void save(Clothing product) {

        try {

            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getIdProduct());
            ps.setString(2, product.getNamaProduct());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getBrand());
            ps.setString(5, product.getUkuran());
            ps.setString(6, product.getWarna());
            ps.setDouble(7, product.getHarga());
            ps.setInt(8, product.getStok());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Clothing product) {

        try {

            String sql = "UPDATE product SET "
                    + "nama_product=?,"
                    + "category=?,"
                    + "brand=?,"
                    + "ukuran=?,"
                    + "warna=?,"
                    + "harga=?,"
                    + "stok=? "
                    + "WHERE id_product=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getNamaProduct());
            ps.setString(2, product.getCategory());
            ps.setString(3, product.getBrand());
            ps.setString(4, product.getUkuran());
            ps.setString(5, product.getWarna());
            ps.setDouble(6, product.getHarga());
            ps.setInt(7, product.getStok());
            ps.setString(8, product.getIdProduct());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String idProduct) {

        try {

            String sql = "DELETE FROM product WHERE id_product=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idProduct);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Clothing> show() {

        List<Clothing> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM product ORDER BY id_product ASC";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Clothing product = new ProductBuilder()

                        .setIdProduct(rs.getString("id_product"))
                        .setNamaProduct(rs.getString("nama_product"))
                        .setCategory(rs.getString("category"))
                        .setBrand(rs.getString("brand"))
                        .setUkuran(rs.getString("ukuran"))
                        .setWarna(rs.getString("warna"))
                        .setHarga(rs.getDouble("harga"))
                        .setStok(rs.getInt("stok"))
                        .build();

                list.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<Clothing> search(String keyword) {

        List<Clothing> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM product WHERE nama_product LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Clothing product = new ProductBuilder()

                        .setIdProduct(rs.getString("id_product"))
                        .setNamaProduct(rs.getString("nama_product"))
                        .setCategory(rs.getString("category"))
                        .setBrand(rs.getString("brand"))
                        .setUkuran(rs.getString("ukuran"))
                        .setWarna(rs.getString("warna"))
                        .setHarga(rs.getDouble("harga"))
                        .setStok(rs.getInt("stok"))
                        .build();

                list.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public String generateId() {

        String id = "PR001";

        try {

            String sql = "SELECT id_product FROM product ORDER BY id_product DESC LIMIT 1";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                String last = rs.getString("id_product");

                int angka = Integer.parseInt(last.substring(2));

                angka++;

                id = String.format("PR%03d", angka);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return id;

    }
    

}