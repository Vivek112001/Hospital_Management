package com.hospital.service;

import java.sql.SQLException;

import com.hospital.dao.PatientDao;
import com.hospital.model.Patient;

public class PatientService {
	private  PatientDao patientDao;
	public void addPatient(Patient patient) {
		patientDao.addPatient(patient);
	}
	
	public Patient getPatient(int id) throws SQLException {
		return patientDao.getPatient(id);
	}

}
