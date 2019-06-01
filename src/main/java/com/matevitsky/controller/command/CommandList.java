package com.matevitsky.controller.command;

import com.matevitsky.repository.impl.ActivityRepositoryImpl;
import com.matevitsky.repository.impl.ActivityRequestRepositoryImpl;
import com.matevitsky.repository.impl.UserRepositoryImpl;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import com.matevitsky.service.impl.ActivityRequestServiceImpl;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.UserServiceImpl;

public enum CommandList {


    LOGIN(new LoginCommand(context.userService, context.activityService)),

    REGISTER(new RegisterCommand(context.userService)),

    USER_ACTIVITY_REMOVE_REQUEST(new UserActivityRemoveCommand(context.activityService)),

    USER_REQUEST_ACTIVITY(new UserRequestActivityCommand(context.activityService, context.activityRequestService)),

    ADMIN_GET_FINISHED_ACTIVITIES(new AdminGetFinishedActivitiesCommand(context.activityService)),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand(context.activityService)),

    ADMIN_CREATE_NEW_ACTIVITY(new AdminCreateNewActivityCommand(context.activityService)),

    ADMIN_ACTIVITY_REQUESTS(new AdminActivityRequestsCommand(context.userService, context.activityService, context.activityRequestService)),

    ADMIN_REMOVE_ACTIVITY(new AdminRemoveActivityCommand(context.activityService)),

    ADMIN_ASSIGN_ACTIVITY_COMMAND(new AdminAssignActivityCommand(context.userService, context.activityService, context.activityRequestService)),

    ERROR(new ErrorCommand()),

    LOGOUT(new LogOutCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand(context.activityService));


    private Command command;

    CommandList(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;

    }

    static class context {
        public final static UserService userService = new UserServiceImpl(new UserRepositoryImpl());
        public final static ActivityRequestService activityRequestService = new ActivityRequestServiceImpl(new ActivityRequestRepositoryImpl());
        public final static ActivityService activityService = new ActivityServiceImpl(new ActivityRepositoryImpl());

    }

}
