package one.ua.commands.commandHelpers.impl;

import one.ua.commands.commandHelpers.CommandHelper;
import one.ua.entity.Speaker;
import one.ua.entity.User;
import org.apache.log4j.Logger;
import one.ua.services.parameterManager.ParameterManager;
import one.ua.services.reportManager.ReportManager;
import one.ua.services.spaekerManager.SpeakerManager;

public class OfferReportHelper implements CommandHelper {
    private Logger logger = Logger.getLogger(OfferReportHelper.class);
    private String theme;
    private User user;

    public OfferReportHelper(String theme, User user) {
        this.theme = theme;
        this.user = user;
    }

    @Override
    public String handle() {
        ParameterManager pm = new ParameterManager();
        if (theme == null || theme.isEmpty()) {
            logger.info("No action done.");
            return "noActionDone";
        }
        if (!pm.isThemeCorrect(theme)) {
            logger.info("Selected incorrect name of theme:" + theme);
            return "errorTheme";
        }
        SpeakerManager speakerManager = new SpeakerManager();
        Speaker speaker = speakerManager.getSpeakerById(user.getId());
        ReportManager reportManager = new ReportManager();
        int result = reportManager.addReport(theme, speaker);
        if (result != 0) {
            logger.info("Was added new theme:" + theme);
            return "successfulChanges";
        }
        logger.info("Was nod added new theme:" + theme);
        return "cabinet";
    }
}
