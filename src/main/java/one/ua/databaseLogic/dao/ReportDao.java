package one.ua.databaseLogic.dao;

import one.ua.entity.Report;
import one.ua.entity.Speaker;

import java.util.List;

public interface ReportDao {

    Long addReport(Report report);

    int addReport(String reportName, Speaker speaker);

    List<Report> getFutureConference(int offset, int maxCount);

    List<Report> getPastConference(int offset, int maxCount);

    List<Report> getOfferedConference(int offset, int maxCount);

    void setAddressForReport(Long addressId, Long reportId);

    int getCountOfFutureReports();

    int getCountOfPastReports();

    int getCountOfOfferedReports();

    int updateReport(Report report);

    int deleteReport(Long reportId);

    void closeConnection();
}
