package one.ua.commands.impl;
import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.AssignPositionHelper;
import one.ua.entity.User;
import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AssignPositionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String position = request.getParameter("userType");
        User currentUser = (User) request.getSession().getAttribute("user");


        AssignPositionHelper helper = new AssignPositionHelper(email, position, currentUser);
        String result = helper.handle();

        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        request.setAttribute("userPosition", position);
        return ConfigManager.getProperty("cabinet");
    }
}
