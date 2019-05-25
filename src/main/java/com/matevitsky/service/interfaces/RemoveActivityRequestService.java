package com.matevitsky.service.interfaces;

import com.matevitsky.entity.RemoveActivityRequest;

import java.util.List;

public interface RemoveActivityRequestService {

    RemoveActivityRequest createRemoveActivityRequest(Integer userId, Integer activityId);

    boolean deleteRemoveActivityRequest(Integer id);

    List<RemoveActivityRequest> getAllRemoveActivityRequests();

    List<RemoveActivityRequest> getAllRemoveActivityRequestsByUserId(Integer userId);
}
