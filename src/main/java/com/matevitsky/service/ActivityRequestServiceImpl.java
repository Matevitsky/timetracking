package com.matevitsky.service;

import com.matevitsky.entity.Request;
import com.matevitsky.repository.ActivityRequestRepository;
import com.matevitsky.repository.ActivityRequestRepositoryImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityRequestServiceImpl implements ActivityRequestService {

    private static Logger LOGGER = Logger.getLogger(ActivityRequestServiceImpl.class);
    ActivityRequestRepository activityRequestRepository = new ActivityRequestRepositoryImpl();

    @Override
    public Request createRequest(Integer userId) {
        LOGGER.debug("Method createRequest with userId " + userId + " started");
        Request request = new Request(userId);
        return activityRequestRepository.create(request);

    }

    @Override
    public boolean deleteRequest(Integer id) {
        LOGGER.debug("Method deleteRequest with Id " + id + " started");
        return activityRequestRepository.delete(id);
    }

    @Override
    public List<Request> getAllRequests() {
        LOGGER.debug("Method getAllRequests started");
        List<Request> requestList = activityRequestRepository.getAll();
        if (requestList.isEmpty()) {
            LOGGER.warn("activityRequestList is empty");
        }
        return requestList;
    }

    @Override
    public List<Request> getAllRequestsByUserId(Integer userId) {
        LOGGER.debug("Method getAllRequestsByUserId started");
        List<Request> requestList = activityRequestRepository.getByUserId(userId);
        if (requestList.isEmpty()) {
            LOGGER.warn("activityRequestList is empty");
        }
        return requestList;
    }
}
