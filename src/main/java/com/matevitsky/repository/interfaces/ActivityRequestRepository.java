package com.matevitsky.repository.interfaces;


import com.matevitsky.entity.ActivityRequest;

import java.util.List;

public interface ActivityRequestRepository extends GenericRepository<ActivityRequest> {
    List<ActivityRequest> getByUserId(Integer id);

    //TODO: подумать чтобы удалить репозиторий активностей

}
