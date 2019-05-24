package com.matevitsky.controller;

public enum CommandList {

    LOGIN(new LoginCommand()),

    USER_ACTIVITY_REMOVE(new UserActivityRemoveCommand()),

    USER_REQUEST_ACTIVITY(new UserRequestActivityCommand()),

    ADMIN_ACTIVITY_REQUESTS(new AdminActivityRequestsCommand()),

    ASSIGN_ACTIVITY_COMMAND(new AdminAssignActivityCommand());







    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

}
