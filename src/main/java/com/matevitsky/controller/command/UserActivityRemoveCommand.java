package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.impl.ActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserActivityRemoveCommand implements Command {

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String activityId = request.getParameter("id");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer duration = Integer.parseInt(request.getParameter("duration"));

        Optional<Activity> activity = activityService.getActivity(Integer.parseInt(activityId));

        if (activity.isPresent()) {
            Activity activityWithDuration = Activity.newBuilder()
                    .withId(activity.get().getId())
                    .withTittle(activity.get().getTitle())
                    .withDescription(activity.get().getDescription())
                    .withDuration(duration)
                    .withStatus(Activity.Status.DONE)
                    .build();
            activityService.updateActivity(activityWithDuration);
        }


        List<Activity> assignedActivityList = activityService.getAssignedActivityList(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", assignedActivityList);
        request.setAttribute("userId", userId);
        //   request.getRequestDispatcher("jsp/userPage.jsp").forward(request, response);
        return USER_PAGE;
    }
}
