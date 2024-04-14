package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.IntToDoubleFunction;

public interface RepoINT<T extends Entity> {
    ArrayList<T> getALL();
    void add(T entity) throws Exception;
    void delete(T entity) throws Exception;
}
