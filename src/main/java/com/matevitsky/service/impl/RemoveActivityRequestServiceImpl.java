package com.matevitsky.service.impl;

import com.matevitsky.entity.RemoveActivityRequest;
import com.matevitsky.repository.impl.RemoveActivityRequestRepositoryImpl;
import com.matevitsky.repository.interfaces.RemoveActivityRequestRepository;
import com.matevitsky.service.interfaces.RemoveActivityRequestService;

import java.util.List;

public class RemoveActivityRequestServiceImpl implements RemoveActivityRequestService {

    //TODO: add logger

    RemoveActivityRequestRepository removeActivityRequestRepository = new RemoveActivityRequestRepositoryImpl();

    @Override
    public boolean createRemoveActivityRequest(Integer userId, Integer activityId) {

        return removeActivityRequestRepository.create(new RemoveActivityRequest(0, userId, activityId));

    }

    @Override
    public boolean deleteRemoveActivityRequest(Integer id) {
        return removeActivityRequestRepository.delete(id);

    }

    @Override
    public List<RemoveActivityRequest> getAllRemoveActivityRequests() {
        return removeActivityRequestRepository.getAll();

    }

    @Override
    public List<RemoveActivityRequest> getAllRemoveActivityRequestsByUserId(Integer userId) {
        return removeActivityRequestRepository.getByUserId(userId);
    }
}
