package DAO;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDAO {

    void save(OrderDetail od);

    List<OrderDetail> show(String order_id);

    void delete(String id);

    void update(OrderDetail od);

    String total(String order_id);

}