package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.services.configManager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigManager.getProperty("error");
    }
}
