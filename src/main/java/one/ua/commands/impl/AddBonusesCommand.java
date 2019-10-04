package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.AddBonusesHelper;
import one.ua.servises.configManager.ConfigManager;
import one.ua.servises.messageManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AddBonusesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String bonuses = request.getParameter("bonuses");
        String email = request.getParameter("email");

        AddBonusesHelper helper = new AddBonusesHelper(bonuses, email);
        String result = helper.handle();

        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));
        return ConfigManager.getProperty("cabinet");
    }
}
