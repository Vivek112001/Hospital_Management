package com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.connection.DatabaseConnection;
import com.hospital.model.Doctor;

public class DoctorDao {
	private Connection connection;
	
	public DoctorDao() {
		try {
			connection=DatabaseConnection.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addDoctor(Doctor doctor) {
		 try {
			 String query = "INSERT INTO doctors (name, specialization, phone) VALUES (?, ?, ?)";
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, doctor.getName());
			 statement.setString(2, doctor.getSpecialization());
			 statement.setString(3, doctor.getPhone());
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Doctor getDoctor(int id) throws SQLException {
		Doctor doctor=null;
		String query="SELECT * FROM doctors WHERE id = ?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet =statement.executeQuery();
		
		if(resultSet.next()) {
			 doctor = new Doctor(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("specialization"),resultSet.getString("phone"));
		}
		resultSet.close();
		statement.close();
		return doctor;
	}
	
	public List<Doctor> getAllDoctors() throws SQLException{
		List<Doctor> doctors = new ArrayList<>();
		
		String query="SELECT * FROM doctors";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			Doctor doctor = new Doctor(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("specialization"),resultSet.getString("phone"));
			doctors.add(doctor);
		}
		return doctors;
	}
}
