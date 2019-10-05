package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.LoginHelper;
import one.ua.entity.User;

import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginHelper helper = new LoginHelper(email, password);
        String result = helper.handle();

        MessageManager message = new MessageManager();
        if (!result.equals("success")) {
            request.setAttribute(result, message.getProperty(result));
            return ConfigManager.getProperty("login");
        }
        String language = helper.getLanguage();
        User user = helper.getUser();
        request.getSession().setAttribute("language", language);
        request.getSession().setAttribute("user", user);
        return ConfigManager.getProperty("cabinet");
    }
}

