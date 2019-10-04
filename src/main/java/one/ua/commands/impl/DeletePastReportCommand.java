package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.DeletePastReportHelper;
import one.ua.entity.Report;
import one.ua.servises.configManager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeletePastReportCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        List<Report> reports = (List<Report>) request.getSession().getAttribute("pastReportList");
        String reportId = request.getParameter("reportId");
        Integer sessionButton = (Integer) request.getSession().getAttribute("pastButton");
        Integer sessionOffset = (Integer) request.getSession().getAttribute("offsetPast");
        Integer sessionMaxCount = (Integer) request.getSession().getAttribute("maxCountPast");

        DeletePastReportHelper helper = new DeletePastReportHelper(
                reports, reportId, sessionButton, sessionOffset, sessionMaxCount);
        String result = helper.handle();

        request.getSession().setAttribute("pastReportList", helper.getPastConferenceList());
        request.getSession().setAttribute("pastButton", helper.getCurrentButton());
        request.getSession().setAttribute("buttonsPast", helper.getButtons());
        request.getSession().setAttribute("pastReportPresence", helper.getPastReportPresence());

        return ConfigManager.getProperty(result);
    }
}
