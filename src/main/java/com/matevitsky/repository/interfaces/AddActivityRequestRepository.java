package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.AddActivityRequest;

import java.util.List;

public interface AddActivityRequestRepository extends GenericRepository<AddActivityRequest> {
    List<AddActivityRequest> getByUserId(Integer id);

}
