package com.matevitsky.repository.db;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E> {

    E insertEntity(E entity);

    E deleteEntity(E entity);

    E updateEntity(E entity);

    Optional<E> getEntity(Integer id);

    List<E> getAll();
}
