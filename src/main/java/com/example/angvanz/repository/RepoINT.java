package com.example.angvanz.repository;

import com.example.angvanz.domain.Entity;

import java.util.List;

public interface RepoINT<T extends Entity> {
    void createTablesIfNotExists();
    void add(T entity) throws Exception;
    void delete(T entity) throws Exception;
    List getData();
    Entity getById(int id);
}
