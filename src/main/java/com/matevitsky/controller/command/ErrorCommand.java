package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ERROR_PAGE;

public class ErrorCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");
        return ERROR_PAGE;
    }
}
