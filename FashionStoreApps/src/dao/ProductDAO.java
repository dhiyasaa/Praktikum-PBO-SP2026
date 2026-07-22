package dao;

import java.util.List;

import model.Clothing;

public interface ProductDAO {

    public void save(Clothing product);

    public void update(Clothing product);

    public void delete(String idProduct);

    public List<Clothing> show();

}