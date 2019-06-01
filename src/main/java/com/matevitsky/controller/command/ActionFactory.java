package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

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

            LOGGER.warn("The command does not exist");
            return new ErrorCommand();
        }
        return current;
    }
}
