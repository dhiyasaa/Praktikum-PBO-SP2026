package DAO;

import java.util.List;

import model.Order;


public interface OrderDao {
	public Order getData(String id);
    void save(Order cs);

    List<Order> show();

    void delete(String id);

    void update(Order cs);

}
