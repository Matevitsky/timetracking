package com.matevitsky.service;


import com.matevitsky.entity.ActivityRequest;

import java.util.List;

public interface ActivityRequestService {

    boolean createActivityRequest(Integer userId);

    boolean deleteActivityRequest(Integer id);

    List<ActivityRequest> getAllActivityRequests();

    List<ActivityRequest> getAllActivityRequestsByUserId(Integer userId);
}
