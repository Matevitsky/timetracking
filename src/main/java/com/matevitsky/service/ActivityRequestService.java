package com.matevitsky.service;


import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.exception.ErrorException;

import java.util.List;

public interface ActivityRequestService {

    boolean createActivityRequest(Integer userId) throws ErrorException;

    boolean deleteActivityRequest(Integer id);

    List<ActivityRequest> getAllActivityRequests();

    List<ActivityRequest> getAllActivityRequestsByUserId(Integer userId);
}
