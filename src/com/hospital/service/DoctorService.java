package com.hospital.service;

import java.sql.SQLException;
import java.util.List;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;

public class DoctorService {
	public DoctorDao doctorDao;
	
	public void addDoctor(Doctor doctor) {
		doctorDao.addDoctor(doctor);
	}
	
	public Doctor getDoctor(int id) throws SQLException {
		return doctorDao.getDoctor(id);
	}
	
	public List<Doctor> getAllDoctors() throws SQLException{
		return doctorDao.getAllDoctors();
	}
}
