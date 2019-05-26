package com.matevitsky.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E> {

    boolean create(E entity);

    boolean delete(Integer id);

    E update(E entity);

    Optional<E> getById(Integer id);

    List<E> getAll();
}
