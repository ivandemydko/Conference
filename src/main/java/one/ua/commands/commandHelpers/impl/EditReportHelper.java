package one.ua.commands.commandHelpers.impl;

import one.ua.commands.commandHelpers.CommandHelper;
import one.ua.entity.Address;
import one.ua.entity.Report;
import one.ua.entity.Speaker;
import org.apache.log4j.Logger;
import one.ua.services.dateTimeManager.DateTimeManager;
import one.ua.services.mailManager.MailManager;
import one.ua.services.parameterManager.ParameterManager;
import one.ua.services.reportManager.ReportManager;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EditReportHelper implements CommandHelper {
    private Logger logger = Logger.getLogger(EditReportHelper.class);
    private String index;
    private String sDate;
    private String sTime;
    private String city;
    private String street;
    private String building;
    private String room;
    private List<Report> reportList;

    public EditReportHelper(String index, String sDate, String sTime,
                            String city, String street, String building, String room, List<Report> reportList) {
        this.index = index;
        this.sDate = sDate;
        this.sTime = sTime;
        this.city = city;
        this.street = street;
        this.building = building;
        this.room = room;
        this.reportList = reportList;
    }

    @Override
    public String handle() {
        ParameterManager parameterManager = new ParameterManager();
        if (parameterManager.isEmpty(sDate, sTime, city, street, building, room)) {
            logger.info("Form was not filled out");
            return "errorEmptyForm";
        }
        Address address = new Address(city, street, building, room);
        if (!parameterManager.isAddressCorrect(address)) {
            logger.info("Address was imputed incorrectly");
            return "errorAddress";
        }
        Report report = reportList.get(Integer.parseInt(index));
        DateTimeManager dateTimeManager = new DateTimeManager();
        Date date = dateTimeManager.fromStringToSqlDate(sDate);
        Time time = dateTimeManager.fromStringToTime(sTime);

        report.setTime(time);
        report.setDate(date);
        report.setAddress(address);
        ReportManager reportManager = new ReportManager();
        int result = reportManager.updateReport(report);
        MailManager mail = new MailManager();
        if (result != 0) {
            Speaker speaker = report.getSpeaker();
            mail.notifySpeakerAppointment(speaker, report);
        }
        logger.info("The report was successfully edited");
        return "successfulChanges";
    }
}
