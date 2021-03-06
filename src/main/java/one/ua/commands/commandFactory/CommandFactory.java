package one.ua.commands.commandFactory;

import one.ua.commands.Command;
import one.ua.commands.commandEnum.CommandEnum;
import one.ua.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.MissingResourceException;

public class CommandFactory {
    /**
     * @return  {@link Command} that is used in Controller
     */
    public Command defineCommand(HttpServletRequest request) {
        Logger logger = Logger.getLogger(CommandFactory.class);
        Command current;
        String action = request.getParameter("command");
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (NullPointerException | MissingResourceException | IllegalArgumentException e) {
            String path = request.getRequestURI() + "?" + request.getQueryString();
            request.setAttribute("wrongAction", path);
            CommandEnum currentEnum = CommandEnum.valueOf("ERROR");
            current = currentEnum.getCurrentCommand();
            User user = (User) request.getSession().getAttribute("user");
            logger.info("Were selected incorrect path: " + path + " by user " + user.getEmail());
            return current;
        }
        return current;
    }
}
