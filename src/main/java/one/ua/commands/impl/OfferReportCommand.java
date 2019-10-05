package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.OfferReportHelper;
import one.ua.entity.User;
import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class OfferReportCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String theme = request.getParameter("theme");
        User user = (User) request.getSession().getAttribute("user");

        OfferReportHelper helper = new OfferReportHelper(theme,user);
        String result = helper.handle();

        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("cabinet");
    }
}
