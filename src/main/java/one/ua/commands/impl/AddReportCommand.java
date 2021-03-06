package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.AddReportHelper;
import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AddReportCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String theme = request.getParameter("theme");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String room = request.getParameter("room");
        String email = request.getParameter("speakerEmail");

        AddReportHelper helper = new AddReportHelper(date, time, theme, city, street, building, room, email);
        String result = helper.handle();
        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("cabinet");
    }
}
