package com.matevitsky.service;

import com.matevitsky.entity.Request;

import java.util.List;

public interface ActivityRequestService {

    Request createRequest(Integer userId);

    boolean deleteRequest(Integer id);

    List<Request> getAllRequests();

    List<Request> getAllRequestsByUserId(Integer userId);
}
