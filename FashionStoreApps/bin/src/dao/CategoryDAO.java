package dao;

import java.util.List;
import model.Category;

public interface CategoryDAO {

    void save(Category category);

    void update(Category category);

    void delete(String id);

    List<Category> show();

}