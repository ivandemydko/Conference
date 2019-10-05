package one.ua.commands.commandHelpers.impl;

import one.ua.commands.commandHelpers.CommandHelper;
import one.ua.entity.User;
import org.apache.log4j.Logger;
import one.ua.services.mailManager.MailManager;
import one.ua.services.parameterManager.ParameterManager;
import one.ua.services.userManager.UserManager;

public class AssignPositionHelper implements CommandHelper {
    private Logger logger = Logger.getLogger(AssignPositionHelper.class);
    private String email;
    private String position;
    private User currentUser;

    public AssignPositionHelper(String email, String position, User user) {
        this.email = email;
        this.position = position;
        currentUser = user;
    }

    @Override
    public String handle() {
        ParameterManager parameterManager = new ParameterManager();
        if (!parameterManager.isEmailCorrect(email)) {
            logger.info("Email was imputed incorrectly: " + email);
            return "errorEmailForm";
        }
        UserManager userManager = new UserManager();
        User user = userManager.getUserByEmail(email);
        if (user == null) {
            logger.info("User with email: " + email + " does not exist");
            return "errorUserNotExists";
        }
        if (user.getPosition().equals(position)) {
            logger.info("User is already on position: " + position);
            return "errorPosition";
        }
        int result = userManager.setUserPosition(user, position);
        MailManager mail = new MailManager();
        if (result != 0) {
            if (!currentUser.equals(user)) {
                user.setPosition(position);
                mail.assignment(user);
            } else {
                currentUser.setPosition(position);
            }
        }
        logger.info("User with email: " + email + " was assign to position: " + position);
        return "successfulChanges";
    }
}
