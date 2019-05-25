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

public class AdminAssignActivityCommand implements Command {


    ActivityService activityService = new ActivityServiceImpl();
    AddActivityRequestService addActivityRequestService = new AddActivityRequestServiceImpl();
    UserService userService = new UserServiceImpl();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String activity = request.getParameter("selectedRecord");
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        String substring = activity.substring(activity.lastIndexOf("id=") + 3, activity.indexOf(","));
        Integer activityId = Integer.parseInt(substring);

        Activity activityForAssign = activityService.getActivity(activityId).get();

        Activity assignedActivity = Activity.newBuilder().withId(activityForAssign.getId())
                .withDescription(activityForAssign.getDescription())
                .withDuration(activityForAssign.getDuration())
                .withTittle(activityForAssign.getTitle())
                .withUserId(userId)
                .withStatus(Activity.Status.ACTIVE).build();
        activityService.updateActivity(assignedActivity);
        addActivityRequestService.deleteAddActivityRequest(activityId);

        List<UserForActivityRequest> userForActivityRequestList = new ArrayList<>();

        List<AddActivityRequest> addActivityRequestList = addActivityRequestService.getAllAddActivityRequests();

        List<Activity> unAssignedActivityList = activityService.getUnAssignedActivityList();


        for (AddActivityRequest addActivityRequest : addActivityRequestList) {
            int userId1 = addActivityRequest.getUserId();
            Optional<User> user = userService.getUser(userId1);
            if (user.isPresent()) {
                UserForActivityRequest userForActivityRequest =
                        new UserForActivityRequest(user.get().getId(), user.get().getName());
                userForActivityRequestList.add(userForActivityRequest);
            }
        }


        Integer userId2 = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userId", userId2);
        request.setAttribute("userForActivityRequestList", userForActivityRequestList);
        request.setAttribute("unAssignedActivityList", unAssignedActivityList);

        return ADMIN_ACTIVITY_REQUESTS_PAGE;
    }
}
