package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.entity.User;
import com.matevitsky.entity.dto.UserForActivityRequest;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_ACTIVITY_REQUESTS_PAGE;

public class AdminAssignActivityCommand implements Command {

    private static final String USER_ID = "userId";

    private final UserService userService;
    private final ActivityService activityService;
    private final ActivityRequestService activityRequestService;
    private static final Logger LOGGER = Logger.getLogger(AdminAssignActivityCommand.class);


    public AdminAssignActivityCommand(UserService userService, ActivityService activityService, ActivityRequestService activityRequestService) {
        this.userService = userService;
        this.activityService = activityService;
        this.activityRequestService = activityRequestService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String activity = request.getParameter("selectedRecord");
        if (activity == null) {
            return buildAdminPageActivityRequestPage(request, activityRequestService, activityService, userService);
        }

        Integer userId = Integer.parseInt(request.getParameter(USER_ID));

        String substring = activity.substring(activity.lastIndexOf("id=") + 3, activity.indexOf(','));
        Integer activityId = Integer.parseInt(substring);

        Activity activityForAssign = activityService.getActivity(activityId).get();

        Activity assignedActivity = Activity.newBuilder().withId(activityForAssign.getId())
                .withDescription(activityForAssign.getDescription())
                .withDuration(activityForAssign.getDuration())
                .withTitle(activityForAssign.getTitle())
                .withUserId(userId)
                .withStatus(Activity.Status.ACTIVE).build();

        activityService.assignActivity(assignedActivity);

        return buildAdminPageActivityRequestPage(request, activityRequestService, activityService, userService);

    }

    private String buildAdminPageActivityRequestPage(HttpServletRequest request, ActivityRequestService activityRequestService, ActivityService activityService, UserService userService) {
        LOGGER.debug("Method buildAdminPageActivityRequestPage");

        List<UserForActivityRequest> userForActivityRequestList = new ArrayList<>();

        List<ActivityRequest> activityRequestList = activityRequestService.getAllActivityRequests();
        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());


        for (ActivityRequest activityRequest : activityRequestList) {
            Integer userId = activityRequest.getUserId();
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                UserForActivityRequest userForActivityRequest =
                        new UserForActivityRequest(user.get().getId(), user.get().getName());
                userForActivityRequestList.add(userForActivityRequest);
            }
        }

        Integer userId = (Integer) request.getSession().getAttribute(USER_ID);
        request.getSession().setAttribute("userId", userId);
        request.setAttribute("userForActivityRequestList", userForActivityRequestList);
        request.setAttribute("unAssignedActivityList", unAssignedActivityList);

        return ADMIN_ACTIVITY_REQUESTS_PAGE;
    }
}