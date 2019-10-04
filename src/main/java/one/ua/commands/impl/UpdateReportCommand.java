package one.ua.commands.impl;


import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.UpdateReportHelper;
import one.ua.entity.Report;
import one.ua.servises.configManager.ConfigManager;
import one.ua.servises.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateReportCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String index = request.getParameter("index");
        String theme = request.getParameter("theme");
        String sDate = request.getParameter("date");
        String sTime = request.getParameter("time");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String room = request.getParameter("room");
        String email = request.getParameter("speakerEmail");
        List<Report> reportList = (List) request.getSession().getAttribute("reportList");

        UpdateReportHelper helper = new UpdateReportHelper(index,
                theme, sDate, sTime, city, street, building, room, email, reportList);

        String result = helper.handle();
        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("updateReport");

    }
}
