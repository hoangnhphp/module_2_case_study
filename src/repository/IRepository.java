package repository;
import entity.Cart;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();

     void save(T o);

     void writeFileBinary(List<T> o);

    List<T> readFileBinary(List<T> l);
}
