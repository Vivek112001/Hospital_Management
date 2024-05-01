package com.hospital.main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hospital.dao.AppointmentDao;
import com.hospital.dao.DoctorDao;
import com.hospital.dao.PatientDao;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

public class HospitalManagement {
	private static Scanner sc = new Scanner(System.in);
	private static PatientDao patientDao=new PatientDao();
	private static DoctorDao doctorDao=new DoctorDao();
	private static AppointmentDao appointmentDao=new AppointmentDao();
	
	public static void addPatient() {
		System.out.println("Add Patient");
		System.out.print("Enter patient name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter patient age: ");
		int age = sc.nextInt();
		
		sc.nextLine(); 
		System.out.print("Enter patient gender: ");
		String gender = sc.nextLine();
		
		System.out.print("Enter patient phone: ");
		String phone = sc.nextLine();
		
		System.out.print("Enter patient address: ");
		String address = sc.nextLine();
		
		Patient patient = new Patient(0, name, age, gender, phone, address);
		patientDao.addPatient(patient);
		 System.out.println("Patient added successfully!");
	}	
	
	public static void addDoctor() {
		System.err.println("Add Doctor");
		System.out.println("Enter the Doctor name");
		String name = sc.nextLine();
		
		System.out.println("Enter his/her specialization");
		String specialization=sc.nextLine();
		
		System.out.println("Enter Phone Number");
		String phone=sc.nextLine();
		
		
		Doctor doctor = new Doctor(0, name, specialization, phone);
		
		doctorDao.addDoctor(doctor);
		
		System.out.println("Doctor added successfully!");
	}
	
	public static void getAllDoctor() {
		try {
			List<Doctor> doctors=doctorDao.getAllDoctors();
			for(Doctor doctor:doctors) {
				System.out.println(doctor.toString());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void getDoctor(){
		System.out.println("Retrieve Doctor By id");
		System.out.println("Enter the Doctor id");
		int doctorId=sc.nextInt();
		
		try {
			Doctor doctor =doctorDao.getDoctor(doctorId) ;
			if(doctor!=null) {
				System.out.println("Doctor with "+doctorId+" = "+doctor.toString());
			}else {
				System.out.println("Patient  is not founded with id"+ doctorId);
			}
		} catch (SQLException e) {
			 System.out.println("Error: Failed to retrieve patient. " + e.getMessage());
		}
	}
	
	public static void scheduleAppointment() {
		System.out.println("Schedule Appointment");
		System.out.print("Enter patient ID: ");
		int patientId = sc.nextInt();
		
		System.out.print("Enter doctor ID: ");
		int doctorId = sc.nextInt();
		sc.nextLine(); 
		
		System.out.print("Enter appointment date (yyyy-MM-dd): ");
		String dateString = sc.nextLine();
		
		Date appointmentDate;
		
		try {
			appointmentDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (Exception e) {
			System.out.println("Error: Invalid date format. Please enter date in yyyy-MM-dd format.");
            return;
		}
		
		Patient patient = new Patient();
		patient.setId(patientId);
		
		Doctor doctor = new Doctor();
		doctor.setId(doctorId);
		
		Appointment appointment = new Appointment(0, patient, doctor, appointmentDate);
		appointmentDao.scheduleAppointment(appointment);
		System.out.println("Appointment scheduled successfully!");
	}
	
	public static void getScheduleAppointment() {
		System.out.println("Retrieve Appointment By id");
		System.out.println("Enter the Appointment id");
		int appointmentId=sc.nextInt();
		sc.nextLine();
		
		try {
			Appointment appointment = appointmentDao.getScheduleAppointment(appointmentId);
			
			if(appointment!=null) {
				System.out.println(appointment.getDoctor().getId());
				System.out.println(appointment.toString());
			}else {
				System.out.println("Appointment id is not founded with "+appointmentId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static void getPatient() {
		System.out.println("Retrieve Patient By id");
		System.out.println("Enter the Patient id");
		int patientId=sc.nextInt();
		
		try {
			Patient patient = patientDao.getPatient(patientId);
			if(patient!=null) {
				System.out.println(patient.toString());
			}else {
				System.out.println("Patient  is not founded with id"+ patientId);
			}
		} catch (SQLException e) {
			 System.out.println("Error: Failed to retrieve patient. " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Hospital Management System \n");
			System.out.println("1. Add Patient, Doctor and Schedule Appointment \n");
			System.out.println("2. get patient by id, get Doctor by id, get Appointment by id \n");
			System.out.println("3. getAllDoctors List \n");
			System.out.println("4. Exit \n");
			System.out.print("Enter your choice: ");
			

			int choice = sc.nextInt();
			sc.nextLine(); 
			
			
			 switch (choice) {
             case 1:
                 addPatient();
            	 addDoctor();
                 scheduleAppointment();
                 break;
             case 2:
            	 getPatient();
            	 getDoctor();
            	 getScheduleAppointment();
            	 break;
             case 3:
            	 getAllDoctor();
             case 4:
                 System.out.println("Exiting...");
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid choice! Please enter a valid option.");
			 }
		}
		
	}
	
}
