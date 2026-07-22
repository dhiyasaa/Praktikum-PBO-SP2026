package dao;

import java.util.List;

import model.Order;

public interface OrderDAO {

    void save(Order order);

    void update(Order order);

    void delete(String idOrder);

    List<Order> show();

}