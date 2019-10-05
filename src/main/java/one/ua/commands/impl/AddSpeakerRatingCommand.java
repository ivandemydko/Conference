package one.ua.commands.impl;

import one.ua.commands.Command;
import one.ua.commands.commandHelpers.impl.AddSpeakerRatingHelper;
import one.ua.services.configManager.ConfigManager;
import one.ua.services.messageManager.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class AddSpeakerRatingCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        String rating = request.getParameter("rating");
        String email = request.getParameter("email");

        AddSpeakerRatingHelper helper = new AddSpeakerRatingHelper(rating, email);
        String result = helper.handle();
        MessageManager message = new MessageManager();
        request.setAttribute(result, message.getProperty(result));

        return ConfigManager.getProperty("cabinet");
    }
}
