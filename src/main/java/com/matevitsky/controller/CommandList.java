package com.matevitsky.controller;

public enum CommandList {

    LOGIN(new LoginCommand()),

    REGISTER(new RegisterCommand()),

    USER_ACTIVITY_REMOVE_REQUEST(new UserActivityRemoveCommand()),

    USER_REQUEST_ACTIVITY(new UserRequestActivityCommand()),

    ADMIN_GET_FINISHED_ACTIVITIES(new AdminGetFinishedActivitiesCommand()),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand()),

    ADMIN_CREATE_NEW_ACTIVITY(new AdminCreateNewActivityCommand()),

    ADMIN_ACTIVITY_REQUESTS(new AdminActivityRequestsCommand()),

    ADMIN_REMOVE_ACTIVITY(new AdminRemoveActivityCommand()),

    ASSIGN_ACTIVITY_COMMAND(new AdminAssignActivityCommand()), //TODO: добавит к имени админ

    ERROR(new ErrorCommand());


    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

}
