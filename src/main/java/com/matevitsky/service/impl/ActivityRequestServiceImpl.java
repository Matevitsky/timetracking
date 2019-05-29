package com.matevitsky.service.impl;


import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.repository.impl.ActivityRequestRepositoryImpl;
import com.matevitsky.repository.interfaces.ActivityRequestRepository;
import com.matevitsky.service.interfaces.ActivityRequestService;
import org.apache.log4j.Logger;

import java.util.List;

public class ActivityRequestServiceImpl implements ActivityRequestService {

    private static Logger LOGGER = Logger.getLogger(ActivityRequestServiceImpl.class);
    ActivityRequestRepository activityRequestRepository = new ActivityRequestRepositoryImpl();

    @Override
    public boolean createActivityRequest(Integer userId) {
        LOGGER.debug("Method createActivityRequest with userId " + userId + " started");
        ActivityRequest activityRequest = new ActivityRequest(0, userId);
        return activityRequestRepository.create(activityRequest);

    }

    @Override
    public boolean deleteActivityRequest(Integer id) {
        LOGGER.debug("Method deleteActivityRequest with Id " + id + " started");
        return activityRequestRepository.delete(id);
    }

    @Override
    public List<ActivityRequest> getAllActivityRequests() {
        LOGGER.debug("Method getAllActivityRequests started");
        List<ActivityRequest> activityRequestList = activityRequestRepository.getAll();
        if (activityRequestList.isEmpty()) {
            LOGGER.warn("ActivityRequestList is empty");
        }
        return activityRequestList;
    }

    @Override
    public List<ActivityRequest> getAllActivityRequestsByUserId(Integer userId) {
        LOGGER.debug("Method getAllActivityRequestsByUserId started");
        List<ActivityRequest> activityRequestList = activityRequestRepository.getByUserId(userId);
        if (activityRequestList.isEmpty()) {
            LOGGER.warn("ActivityRequestList is empty");
        }
        return activityRequestList;
    }
}
