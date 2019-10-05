package one.ua.tags;

import one.ua.entity.Speaker;
import one.ua.entity.User;
import org.apache.log4j.Logger;
import one.ua.services.spaekerManager.SpeakerManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class GetBonusesTag extends TagSupport {
    private Logger logger = Logger.getLogger(GetBonusesTag.class);

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute("user");
        SpeakerManager speakerManager = new SpeakerManager();
        Speaker speaker = speakerManager.getSpeakerById(user.getId());
        int bonuses = speakerManager.getSpeakerBonuses(speaker);
        String str = Integer.toString(bonuses);
        try {
            JspWriter writer = pageContext.getOut();
            writer.write(str);
        } catch (IOException e) {
            logger.error(e);
        }
        return SKIP_BODY;
    }
}
