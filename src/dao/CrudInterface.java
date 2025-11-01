package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T> {
    void save(T u) throws SQLException;
    void update(int id) throws SQLException;
    List<T> get() throws SQLException;
    void delete(int id) throws SQLException;
}
