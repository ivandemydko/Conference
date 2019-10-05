package speakerManager;

import one.ua.entity.Speaker;
import org.junit.*;
import one.ua.services.spaekerManager.SpeakerManager;

public class SpeakerManagerTest {
    private static SpeakerManager speakerManager;

    @Before
    public void init() {
        speakerManager = new SpeakerManager();
    }

    @After
    public void clear() {
        speakerManager = null;
    }

    @Test
    public void setSpeakerBonusesTest() {
        Speaker speaker = new Speaker();
        speaker.setRating(3);
        int bonuses = speakerManager.setSpeakerBonuses(100, speaker);
        Assert.assertEquals(130, bonuses);
    }

}
