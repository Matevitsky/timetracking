package com.matevitsky.repository;

import com.matevitsky.entity.Request;

import java.util.List;

public interface ActivityRequestRepository extends GenericRepository<Request> {
    List<Request> getByUserId(Integer id);

}
