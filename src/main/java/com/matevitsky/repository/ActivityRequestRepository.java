package com.matevitsky.repository;

import com.matevitsky.entity.Request;

import java.util.List;

public interface ActivityRequestRepository {
    List<Request> getByUserId(Integer id);

}
