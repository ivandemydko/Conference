package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.entity.User;
import org.apache.log4j.Logger;
import one.ua.services.configManager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Logger logger = Logger.getLogger(LogoutCommand.class);
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().invalidate();
        logger.info("User " + user.getEmail() + " logged out of session");
        return ConfigManager.getProperty("index");
    }
}
