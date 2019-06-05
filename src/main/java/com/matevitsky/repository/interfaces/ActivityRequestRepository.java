package com.matevitsky.repository.interfaces;


import com.matevitsky.entity.ActivityRequest;

import java.util.List;

public interface ActivityRequestRepository extends GenericRepository<ActivityRequest> {
    /**
     * Get activity request for specific user
     *
     * @param id
     * @return List of activity requests
     */
    List<ActivityRequest> getByUserId(Integer id);


}
