package com.matevitsky.service;

import com.matevitsky.entity.ActivityRequest;

import java.util.List;

public interface ActivityRequestService {

    ActivityRequest createRequest(Integer userId);

    boolean deleteRequest(Integer id);

    List<ActivityRequest> getAllRequests();

    List<ActivityRequest> getAllRequestsByUserId(Integer userId);
}
