package com.matevitsky.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E> {

    boolean create(E entity);

    boolean delete(Integer id); //TODO: replace with -delete by Id

    E update(E entity); //replace with optional


    Optional<E> getById(Integer id);

    List<E> getAll();
}
