package one.ua.services.spaekerManager;

import one.ua.databaseLogic.dao.SpeakerDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.Speaker;


/**
 * This class encapsulated some methods from {@link SpeakerDao}
 */
public class SpeakerManager {
    private SpeakerDao speakerDao;

    public int setSpeakerBonuses(int bonuses, Speaker speaker) {
        if (speaker.getRating() == 0) {
            return bonuses;
        }
        double result = bonuses + bonuses * (speaker.getRating() / 10.0);
        return (int) result;
    }

    public Speaker getSpeakerByEmail(String email) {
        speakerDao = DaoFactory.getSpeakerDao();
        Speaker speaker = speakerDao.getSpeakerByEmail(email);
        speakerDao.closeConnection();
        return speaker;
    }

    public int addBonusesToSpeaker(Speaker speaker, int allBonuses) {
        speakerDao = DaoFactory.getSpeakerDao();
        int result = speakerDao.addBonusesToSpeaker(speaker, allBonuses);
        speakerDao.closeConnection();
        return result;
    }

    public int addSpeakerRating(Speaker speaker, int rating) {
        speakerDao = DaoFactory.getSpeakerDao();
        int result = speakerDao.addSpeakerRating(speaker, rating);
        speakerDao.closeConnection();
        return result;
    }

    public Speaker getSpeakerById(Long speakerId) {
        speakerDao = DaoFactory.getSpeakerDao();
        Speaker speaker = speakerDao.getSpeakerById(speakerId);
        speakerDao.closeConnection();
        return speaker;
    }

    public int getSpeakerBonuses(Speaker speaker) {
        speakerDao = DaoFactory.getSpeakerDao();
        int bonuses = speakerDao.getSpeakerBonuses(speaker);
        speakerDao.closeConnection();
        return bonuses;
    }
}
