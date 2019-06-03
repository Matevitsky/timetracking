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


    LOGIN(new LoginCommand(Context.userService, Context.activityService)),

    REGISTER(new RegisterCommand(Context.userService)),

    USER_ACTIVITY_REMOVE_REQUEST(new UserActivityRemoveCommand(Context.activityService)),

    USER_REQUEST_ACTIVITY(new UserRequestActivityCommand(Context.activityService, Context.activityRequestService)),

    ADMIN_GET_FINISHED_ACTIVITIES(new AdminGetFinishedActivitiesCommand(Context.activityService)),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand(Context.activityService)),

    ADMIN_CREATE_NEW_ACTIVITY(new AdminCreateNewActivityCommand(Context.activityService)),

    ADMIN_ACTIVITY_REQUESTS(new AdminActivityRequestsCommand(Context.userService, Context.activityService, Context.activityRequestService)),

    ADMIN_REMOVE_ACTIVITY(new AdminRemoveActivityCommand(Context.activityService)),

    ADMIN_ASSIGN_ACTIVITY_COMMAND(new AdminAssignActivityCommand(Context.userService, Context.activityService, Context.activityRequestService)),

    ERROR(new ErrorCommand()),

    LOGOUT(new LogOutCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand(Context.activityService));


    private Command command;

    CommandList(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;

    }

    static class Context {
        public static final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
        public static final ActivityRequestService activityRequestService = new ActivityRequestServiceImpl(new ActivityRequestRepositoryImpl());
        public static final ActivityService activityService = new ActivityServiceImpl(new ActivityRepositoryImpl());

        private Context() {
        }

    }

}
