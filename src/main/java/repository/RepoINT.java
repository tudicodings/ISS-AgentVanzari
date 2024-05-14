package repository;

import domain.Entity;

import java.util.List;

public interface RepoINT<T extends Entity> {
    void createTablesIfNotExists();
    void add(T entity) throws Exception;
    void delete(T entity) throws Exception;
    List getData();
    Entity getById(int id);
}
