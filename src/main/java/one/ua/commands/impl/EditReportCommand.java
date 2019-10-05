package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.EditReportHelper;
import one.ua.entity.Report;
import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String index = request.getParameter("index");
        String sDate = request.getParameter("date");
        String sTime = request.getParameter("time");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String room = request.getParameter("room");
        List<Report> reportList = (List) request.getSession().getAttribute("offeredReportList");

        EditReportHelper helper = new EditReportHelper(index, sDate, sTime, city, street, building, room, reportList);
        String result = helper.handle();
        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("editReport");
    }
}
