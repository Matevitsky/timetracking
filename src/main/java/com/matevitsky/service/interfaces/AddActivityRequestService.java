package com.matevitsky.service.interfaces;

import com.matevitsky.entity.AddActivityRequest;

import java.util.List;

public interface AddActivityRequestService {

    boolean createAddActivityRequest(Integer userId);

    boolean deleteAddActivityRequest(Integer id);

    List<AddActivityRequest> getAllAddActivityRequests();

    List<AddActivityRequest> getAllAddActivityRequestsByUserId(Integer userId);
}
