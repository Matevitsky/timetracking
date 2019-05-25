package com.matevitsky.service.impl;

import com.matevitsky.entity.AddActivityRequest;
import com.matevitsky.repository.impl.AddActivityRequestRepositoryImpl;
import com.matevitsky.repository.interfaces.AddActivityRequestRepository;
import com.matevitsky.service.interfaces.AddActivityRequestService;
import org.apache.log4j.Logger;

import java.util.List;

public class AddActivityRequestServiceImpl implements AddActivityRequestService {

    private static Logger LOGGER = Logger.getLogger(AddActivityRequestServiceImpl.class);
    AddActivityRequestRepository addActivityRequestRepository = new AddActivityRequestRepositoryImpl();

    @Override
    public AddActivityRequest createAddActivityRequest(Integer userId) {
        LOGGER.debug("Method createAddActivityRequest with userId " + userId + " started");
        AddActivityRequest addActivityRequest = new AddActivityRequest(0, userId);
        return addActivityRequestRepository.create(addActivityRequest);

    }

    @Override
    public boolean deleteAddActivityRequest(Integer id) {
        LOGGER.debug("Method deleteAddActivityRequest with Id " + id + " started");
        return addActivityRequestRepository.delete(id);
    }

    @Override
    public List<AddActivityRequest> getAllAddActivityRequests() {
        LOGGER.debug("Method getAllAddActivityRequests started");
        List<AddActivityRequest> addActivityRequestList = addActivityRequestRepository.getAll();
        if (addActivityRequestList.isEmpty()) {
            LOGGER.warn("addActivityRequestList is empty");
        }
        return addActivityRequestList;
    }

    @Override
    public List<AddActivityRequest> getAllAddActivityRequestsByUserId(Integer userId) {
        LOGGER.debug("Method getAllAddActivityRequestsByUserId started");
        List<AddActivityRequest> addActivityRequestList = addActivityRequestRepository.getByUserId(userId);
        if (addActivityRequestList.isEmpty()) {
            LOGGER.warn("addActivityRequestList is empty");
        }
        return addActivityRequestList;
    }
}
