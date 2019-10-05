package one.ua.services.paginationManager;

import one.ua.entity.Report;
import one.ua.services.reportManager.ReportManager;

import java.util.ArrayList;
import java.util.List;

public class PaginationManager {
    private String requestButton;
    private String requestMaxCount;
    private Integer sessionOffset;
    private Integer sessionMaxCount;

    private int offset;
    private List<Report> conferenceList;
    private int maxCount;
    private List<Integer> buttons;
    private Integer sessionButton;

    private int countOfReports;
    private ReportManager reportManager = new ReportManager();
    private final int INITIAL_SIZE = 5;
    private final int INITIAL_OFFSET = 0;

    public PaginationManager(String requestButton, Integer sessionButton, String requestMaxCount, Integer sessionOffset, Integer sessionMaxCount) {
        this.requestButton = requestButton;
        this.sessionButton = sessionButton;
        this.requestMaxCount = requestMaxCount;
        this.sessionOffset = sessionOffset;
        this.sessionMaxCount = sessionMaxCount;
    }

    /**
     * Defines data for pagination.
     *
     * @param report defines certain conference list for pagination, can take values: "future", "past", "offered".
     */
    public void pagination(String report) {
        getCountOfReports(report);
        if (requestButton == null && requestMaxCount == null) {
            maxCount = (sessionMaxCount != null) ? sessionMaxCount : INITIAL_SIZE;
            offset = (sessionOffset != null) ? sessionOffset : INITIAL_OFFSET;
        } else {
            if (requestMaxCount == null) {
                maxCount = sessionMaxCount == null ? INITIAL_SIZE : sessionMaxCount;
            } else {
                maxCount = Integer.parseInt(requestMaxCount);
            }
            if (requestButton != null) {
                offset = Integer.parseInt(requestButton) * maxCount - maxCount;
            } else if (sessionOffset != null) {
                offset = sessionOffset / maxCount * maxCount;
            } else {
                offset = INITIAL_OFFSET;
            }
        }

        selectConference(report);
        buttons = getButtons(countOfReports, maxCount);

        if (offset >= countOfReports && countOfReports > 0) {
            offset = countOfReports - maxCount;
            selectConference(report);
            buttons = getButtons(countOfReports, maxCount);
            if (sessionButton != null) {
                sessionButton -= 1;
            }
        }
    }

    private void selectConference(String report) {
        if (report.equals("future")) {
            conferenceList = reportManager.getFutureConference(offset, maxCount);
        } else if (report.equals("past")) {
            conferenceList = reportManager.getPastConference(offset, maxCount);
        } else {
            conferenceList = reportManager.getOfferedConference(offset, maxCount);
        }
    }

    private void getCountOfReports(String report) {
        if (report.equals("future")) {
            countOfReports = reportManager.getCountOfFutureReports();
        } else if (report.equals("past")) {
            countOfReports = reportManager.getCountOfPastReports();
        } else {
            countOfReports = reportManager.getCountOfOfferedReports();
        }
    }

    private List<Integer> getButtons(int amountOfReports, int maxCountPerPage) {
        double buttons = amountOfReports / (double) maxCountPerPage;
        buttons = Math.ceil(buttons);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= buttons; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * This method helps to define desired button when number of elements per page had been changed.
     *
     * @return returns the number of the button that is used at a particular moment in time.
     */
    public int getCurrentButton() {
        if (requestMaxCount != null) {
            sessionButton = sessionButton == null ? 0 : sessionButton;
            return (int) Math.ceil((sessionButton * sessionMaxCount - sessionMaxCount + 1) / Double.parseDouble(requestMaxCount));
        }
        if (requestButton != null) {
            return Integer.parseInt(requestButton);
        }
        if (sessionButton != null) {
            return sessionButton;
        }
        return 0;
    }

    public int getOffset() {
        return offset;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public List<Report> getConferenceList() {
        return conferenceList;
    }

    public List<Integer> getButtons() {
        return buttons;
    }
}
