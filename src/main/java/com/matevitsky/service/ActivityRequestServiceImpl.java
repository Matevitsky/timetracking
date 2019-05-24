package com.matevitsky.service;

import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.repository.ActivityRequestRepository;
import com.matevitsky.repository.ActivityRequestRepositoryImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityRequestServiceImpl implements ActivityRequestService {

    private static Logger LOGGER = Logger.getLogger(ActivityRequestServiceImpl.class);
    ActivityRequestRepository activityRequestRepository = new ActivityRequestRepositoryImpl();

    @Override
    public ActivityRequest createRequest(Integer userId) {
        LOGGER.debug("Method createRequest with userId " + userId + " started");
        ActivityRequest activityRequest = new ActivityRequest(0, userId);
        return activityRequestRepository.create(activityRequest);

    }

    @Override
    public boolean deleteRequest(Integer id) {
        LOGGER.debug("Method deleteRequest with Id " + id + " started");
        return activityRequestRepository.delete(id);
    }

    @Override
    public List<ActivityRequest> getAllRequests() {
        LOGGER.debug("Method getAllRequests started");
        List<ActivityRequest> activityRequestList = activityRequestRepository.getAll();
        if (activityRequestList.isEmpty()) {
            LOGGER.warn("activityRequestList is empty");
        }
        return activityRequestList;
    }

    @Override
    public List<ActivityRequest> getAllRequestsByUserId(Integer userId) {
        LOGGER.debug("Method getAllRequestsByUserId started");
        List<ActivityRequest> activityRequestList = activityRequestRepository.getByUserId(userId);
        if (activityRequestList.isEmpty()) {
            LOGGER.warn("activityRequestList is empty");
        }
        return activityRequestList;
    }
}
