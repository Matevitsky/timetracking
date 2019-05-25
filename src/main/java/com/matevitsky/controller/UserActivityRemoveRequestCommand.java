package com.matevitsky.controller;

import com.matevitsky.service.impl.RemoveActivityRequestServiceImpl;
import com.matevitsky.service.interfaces.RemoveActivityRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserActivityRemoveRequestCommand implements Command {

    //TODO: вставить логгер во всех контроллерах
    RemoveActivityRequestService removeActivityRequestService = new RemoveActivityRequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer activityId = Integer.parseInt(request.getParameter("id"));
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        removeActivityRequestService.createRemoveActivityRequest(userId, activityId);

        return null;
    }
}
