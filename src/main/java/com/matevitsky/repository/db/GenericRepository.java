package com.matevitsky.repository.db;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E> {

    E insertEntity(E entity);

    boolean deleteEntity(Integer id);

    E updateEntity(E entity);

    Optional<E> getEntity(Integer id);

    List<E> getAll();
}
