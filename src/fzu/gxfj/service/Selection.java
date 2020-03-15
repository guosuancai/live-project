package fzu.gxfj.service;

import fzu.gxfj.dao.AppointmentDAO;
import fzu.gxfj.dao.AppointmentInfoDAO;
import fzu.gxfj.pojo.Appointment;
import fzu.gxfj.pojo.AppointmentInfo;

import java.util.Collections;
import java.util.List;

public class Selection {

    /**
     * 抽选预约者
     */
    public void select(AppointmentInfo newestInfo) {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        List<Appointment> appointmentList = appointmentDAO.listAppointedThisTurn();
        int maxMask = newestInfo.getMaskNum();
        Collections.shuffle(appointmentList);
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentNum() <= maxMask) {
                appointment.setWin(true);
                maxMask -= appointment.getAppointmentNum();
                appointmentDAO.update(appointment);
            }
        }
    }
}
