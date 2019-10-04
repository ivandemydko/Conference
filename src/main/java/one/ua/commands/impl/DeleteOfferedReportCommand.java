package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.DeleteOfferedReportHelper;
import one.ua.entity.Report;
import one.ua.servises.configManager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteOfferedReportCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        List<Report> reports = (List<Report>) request.getSession().getAttribute("offeredReportList");
        String reportId = request.getParameter("reportId");
        Integer sessionButton = (Integer) request.getSession().getAttribute("offeredButton");
        Integer sessionOffset = (Integer) request.getSession().getAttribute("offsetOffered");
        Integer sessionMaxCount = (Integer) request.getSession().getAttribute("maxCountOffered");

        DeleteOfferedReportHelper helper = new DeleteOfferedReportHelper(reports, reportId,
                sessionButton, sessionOffset, sessionMaxCount);
        String result = helper.handle();

        request.getSession().setAttribute("offeredReportList", helper.getOfferedConferenceList());
        request.getSession().setAttribute("offeredButton", helper.getCurrentButton());
        request.getSession().setAttribute("buttonsOffered", helper.getButtons());

        return ConfigManager.getProperty(result);
    }
}
