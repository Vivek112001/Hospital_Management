package com.hospital.service;

import java.sql.SQLException;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;

public class AppointmentService {
	private AppointmentDao appointmentDao;
	
	public void scheduleAppointment(Appointment appointment) {
		appointmentDao.scheduleAppointment(appointment);
	}
	
	public Appointment getScheduleAppointment(int id) throws SQLException {
		return appointmentDao.getScheduleAppointment(id);
	}
}
