package News.services;

import java.util.Collection;

public interface CRUDService<T> {

    T getById(int id);
    Collection<T> getAll();
    void create(T news);
    void update(int id, T news);
    void deleteById(int id);
}
