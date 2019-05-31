package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.entity.User;
import com.matevitsky.entity.dto.UserForActivityRequest;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import com.matevitsky.service.impl.ActivityRequestServiceImpl;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_ACTIVITY_REQUESTS_PAGE;

public class AdminAssignActivityCommand implements Command {


    ActivityService activityService = new ActivityServiceImpl();
    ActivityRequestService activityRequestService = new ActivityRequestServiceImpl();
    UserService userService = new UserServiceImpl();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

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
        activityRequestService.deleteActivityRequest(activityId);

        List<UserForActivityRequest> userForActivityRequestList = new ArrayList<>();

        List<ActivityRequest> activityRequestList = activityRequestService.getAllActivityRequests();

        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());


        for (ActivityRequest activityRequest : activityRequestList) {
            int userId1 = activityRequest.getUserId();
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
