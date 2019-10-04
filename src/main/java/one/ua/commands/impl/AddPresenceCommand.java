package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.AddPresenceHelper;
import one.ua.entity.Report;
import one.ua.servises.configManager.ConfigManager;
import one.ua.servises.messageManager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AddPresenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String index = request.getParameter("index");
        String count = request.getParameter("presence");
        List<Report> pastReportList = (List<Report>) request.getSession().getAttribute("pastReportList");
        Map<Long, Integer> pastReportPresence = (Map<Long, Integer>) request.getSession().getAttribute("pastReportPresence");

        AddPresenceHelper helper = new AddPresenceHelper(pastReportList, pastReportPresence, index, count);
        String result = helper.handle();

        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("pastReports");
    }
}
