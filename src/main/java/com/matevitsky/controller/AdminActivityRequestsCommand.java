package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.AddActivityRequest;
import com.matevitsky.entity.User;
import com.matevitsky.entity.dto.UserForActivityRequest;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.AddActivityRequestServiceImpl;
import com.matevitsky.service.impl.UserServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;
import com.matevitsky.service.interfaces.AddActivityRequestService;
import com.matevitsky.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_ACTIVITY_REQUESTS_PAGE;

public class AdminActivityRequestsCommand implements Command {
    AddActivityRequestService addActivityRequestService = new AddActivityRequestServiceImpl();
    ActivityService activityService = new ActivityServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserForActivityRequest> userForActivityRequestList = new ArrayList<>();

        List<AddActivityRequest> addActivityRequestList = addActivityRequestService.getAllAddActivityRequests();
        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());


        for (AddActivityRequest addActivityRequest : addActivityRequestList) {
            int userId = addActivityRequest.getUserId();
            Optional<User> user = userService.getUser(userId);
            if (user.isPresent()) {
                UserForActivityRequest userForActivityRequest =
                        new UserForActivityRequest(user.get().getId(), user.get().getName());
                userForActivityRequestList.add(userForActivityRequest);
            }
        }


        Integer userId = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userId", userId);
        request.setAttribute("userForActivityRequestList", userForActivityRequestList);
        request.setAttribute("unAssignedActivityList", unAssignedActivityList);

        return ADMIN_ACTIVITY_REQUESTS_PAGE;

    }
}
