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

public class AdminActivityRequestsPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminActivityRequestsPageCommand.class);

    private final UserService userService;
    private final ActivityRequestService activityRequestService;
    private final ActivityService activityService;

    public AdminActivityRequestsPageCommand(UserService userService, ActivityService activityService, ActivityRequestService activityRequestService) {
        this.userService = userService;
        this.activityService = activityService;
        this.activityRequestService = activityRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

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

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userId", userId);
        request.setAttribute("userForActivityRequestList", userForActivityRequestList);
        request.setAttribute("unAssignedActivityList", unAssignedActivityList);

        return ADMIN_ACTIVITY_REQUESTS_PAGE;

    }
}
