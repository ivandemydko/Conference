package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.ShowOfferedReportsHelper;
import one.ua.servises.configManager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ShowOfferedReportsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String requestButton = request.getParameter("button");
        String requestMaxCount = request.getParameter("maxCount");
        Integer sessionButton = (Integer) request.getSession().getAttribute("offeredButton");
        Integer sessionOffset = (Integer) request.getSession().getAttribute("offsetOffered");
        Integer sessionMaxCount = (Integer) request.getSession().getAttribute("maxCountOffered");

        ShowOfferedReportsHelper helper = new ShowOfferedReportsHelper(
                requestButton, requestMaxCount, sessionButton, sessionOffset, sessionMaxCount);
        String result = helper.handle();

        request.getSession().setAttribute("offeredReportList", helper.getOfferedConferenceList());
        request.getSession().setAttribute("maxCountOffered", helper.getMaxCount());
        request.getSession().setAttribute("offsetOffered", helper.getOffset());
        request.getSession().setAttribute("offeredButton", helper.getCurrentButton());
        request.getSession().setAttribute("buttonsOffered", helper.getButtons());

        return ConfigManager.getProperty(result);
    }
}
