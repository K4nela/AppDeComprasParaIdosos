package dao;

import java.sql.SQLException;
import java.util.List;

public interface Base<T> {
    void save(T u) throws SQLException;
    void update(T u) throws SQLException;
    List<T> get() throws SQLException;
    void delete(T u) throws SQLException;
}
