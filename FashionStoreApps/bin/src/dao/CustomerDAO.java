package dao;

import java.util.List;

import model.Customer;

public interface CustomerDAO {

    void save(Customer customer);

    void update(Customer customer);

    void delete(String idCustomer);

    List<Customer> show();

}