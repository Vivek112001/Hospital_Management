package com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hospital.connection.DatabaseConnection;
import com.hospital.model.Patient;

public class PatientDao {
	private Connection connection;
	
	public PatientDao() {
		try {
			connection=DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPatient(Patient patient) {
		try {
			String query="INSERT INTO patients (name, age, gender, phone, address) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, patient.getName());
	        statement.setInt(2, patient.getAge());
	        statement.setString(3, patient.getGender());
	        statement.setString(4, patient.getPhone());
	        statement.setString(5, patient.getAddress());
	        statement.executeUpdate();
	        statement.close();
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Patient getPatient(int id) throws SQLException{
		Patient patient=null;
		String query="SELECT * FROM patients WHERE id = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet resultSet =statement.executeQuery();
		
		if(resultSet.next()) {
			 int patientId = resultSet.getInt("id");
			 String name = resultSet.getString("name");
			 int age = resultSet.getInt("age");
			 String gender = resultSet.getString("gender");
			 String phone = resultSet.getString("phone");
			 String address = resultSet.getString("address");
			 patient=new Patient(patientId, name, age, gender, phone, address);
		}
		
		resultSet.close();
		statement.close();
		return patient;
		
	}
}
