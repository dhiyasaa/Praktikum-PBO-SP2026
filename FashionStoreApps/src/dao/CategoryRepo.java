package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Database;
import model.Category;

public class CategoryRepo implements CategoryDAO{

    Connection conn = Database.koneksi();

    @Override
    public void save(Category category) {

        try {

            String sql="INSERT INTO category VALUES(?,?)";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1, category.getIdCategory());
            ps.setString(2, category.getNamaCategory());

            ps.executeUpdate();

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public void update(Category category) {

        try {

            String sql="UPDATE category SET nama_category=? WHERE id_category=?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getIdCategory());

            ps.executeUpdate();

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public void delete(String id) {

        try {

            String sql="DELETE FROM category WHERE id_category=?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,id);

            ps.executeUpdate();

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public List<Category> show() {

        List<Category> list=new ArrayList<>();

        try{

            String sql="SELECT * FROM category";

            PreparedStatement ps=conn.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Category c=new Category();

                c.setIdCategory(rs.getString("id_category"));
                c.setNamaCategory(rs.getString("nama_category"));

                list.add(c);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }

}