package com.matevitsky.controller;

public enum CommandList {

    LOGIN(new LoginCommand()),

    USER_ACTIVITY_REMOVE(new UserActivityRemoveCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

}
