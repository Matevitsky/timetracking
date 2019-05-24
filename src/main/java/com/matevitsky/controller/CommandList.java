package com.matevitsky.controller;

public enum CommandList {

    LOGIN(new LoginCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

}
