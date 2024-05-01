package com.hospital.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hospital.connection.DatabaseConnection;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

public class AppointmentDao {
	private Connection connection;
	PatientDao patientDao = new PatientDao();
	DoctorDao doctorDao = new DoctorDao();

	
	public AppointmentDao() {
		try {
			connection=DatabaseConnection.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public  void scheduleAppointment(Appointment appointment) {
		try {
			 String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setInt(1, appointment.getPatient().getId());
			 statement.setInt(2, appointment.getDoctor().getId());
			 statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Appointment getScheduleAppointment(int id) throws SQLException {
		Appointment appointment =null;
		String query="SELECT * FROM appointments WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			Patient patient = patientDao.getPatient(resultSet.getInt("patient_id"));
			Doctor doctor = doctorDao.getDoctor(resultSet.getInt("doctor_id"));
			Date appointmentDate = resultSet.getDate("appointment_date");
			appointment=new Appointment(resultSet.getInt(id),patient,doctor,appointmentDate);
		}
		
		return appointment;
	}
}
