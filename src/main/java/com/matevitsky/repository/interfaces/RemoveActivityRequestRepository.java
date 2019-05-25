package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.RemoveActivityRequest;

import java.util.List;

public interface RemoveActivityRequestRepository extends GenericRepository<RemoveActivityRequest> {

    List<RemoveActivityRequest> getByUserId(Integer id);

}
