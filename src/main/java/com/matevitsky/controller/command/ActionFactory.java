package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public Command defineCommand(HttpServletRequest request) {
        Command current = null;
        String action = request.getParameter("command");
        if (action == null) {
            return current;
        }
        try {
            CommandList command = CommandList.valueOf(action.toUpperCase());
            current = command.getCommand();
        } catch (IllegalArgumentException e) {
            //TODO: написать логер
            return current = new ErrorCommand();
        }
        return current;
    }
}
