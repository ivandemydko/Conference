package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.services.configManager.ConfigManager;
import javax.servlet.http.HttpServletRequest;

public class ReportIndexCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        String index = request.getParameter("index");
        String requestURI = request.getParameter("requestURI");
        request.getSession().setAttribute("index", index);
        if (requestURI != null) {
            return ConfigManager.getProperty("editReport");
        }
        return ConfigManager.getProperty("updateReport");
    }
}
