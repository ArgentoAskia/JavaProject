package dao2;

import java.util.List;

public interface CommonDAO<T> {

    List<T> selectAll();
    T selectById(Integer id);
    int insert(T data);
    int delete(T data);
    int update(T data);
}
