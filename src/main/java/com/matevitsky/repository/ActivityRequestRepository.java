package com.matevitsky.repository;

import com.matevitsky.entity.ActivityRequest;

import java.util.List;

public interface ActivityRequestRepository extends GenericRepository<ActivityRequest> {
    List<ActivityRequest> getByUserId(Integer id);

}
