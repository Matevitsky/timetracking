package com.matevitsky.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E> {

    boolean create(E entity);

    boolean deleteById(Integer id);

    E update(E entity); //replace with optional

    Optional<E> getById(Integer id);

    List<E> getAll();
}
