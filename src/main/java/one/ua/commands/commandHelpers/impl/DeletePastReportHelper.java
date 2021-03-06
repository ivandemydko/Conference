package one.ua.commands.commandHelpers.impl;

import one.ua.commands.commandHelpers.CommandHelper;
import one.ua.entity.Report;
import org.apache.log4j.Logger;
import one.ua.services.paginationManager.PaginationManager;
import one.ua.services.presenceManager.PresenceManager;
import one.ua.services.reportManager.ReportManager;

import java.util.List;
import java.util.Map;

public class DeletePastReportHelper implements CommandHelper {
    private Logger logger = Logger.getLogger(EditReportHelper.class);
    private List<Report> reports;
    private String reportId;
    private Integer sessionButton;
    private Integer sessionOffset;
    private Integer sessionMaxCount;

    private List<Integer> buttons;
    private List<Report> pastConferenceList;
    private int currentButton;
    private Map<Long, Integer> pastReportPresence;

    public DeletePastReportHelper(List<Report> reports, String reportId, Integer sessionButton, Integer sessionOffset, Integer sessionMaxCount) {
        this.reports = reports;
        this.reportId = reportId;
        this.sessionButton = sessionButton;
        this.sessionOffset = sessionOffset;
        this.sessionMaxCount = sessionMaxCount;
    }

    @Override
    public String handle() {
        ReportManager reportManager = new ReportManager();
        reportManager.deleteReport(reports, Long.parseLong(reportId));
        PaginationManager pm = new PaginationManager(null,
                sessionButton, null, sessionOffset, sessionMaxCount);
        pm.pagination("past");
        buttons = pm.getButtons();
        pastConferenceList = pm.getConferenceList();
        presence();
        currentButton = pm.getCurrentButton();
        logger.info("Past report was deleted");
        return "pastReports";

    }

    private void presence() {
        PresenceManager presenceManager = new PresenceManager();
        pastReportPresence = presenceManager.getPresence(pastConferenceList);
    }

    public List<Integer> getButtons() {
        return buttons;
    }

    public Map<Long, Integer> getPastReportPresence() {
        return pastReportPresence;
    }

    public List<Report> getPastConferenceList() {
        return pastConferenceList;
    }

    public int getCurrentButton() {
        return currentButton;
    }
}
