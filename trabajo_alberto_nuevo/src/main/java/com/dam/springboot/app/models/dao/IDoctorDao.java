package com.dam.springboot.app.models.dao;

import java.util.List;

import com.dam.springboot.app.models.entity.Doctor;

public interface IDoctorDao {
	
	public List<Doctor> findAll();

	public void save(Doctor doctor);
	
	public Doctor findOne(String dni);
	
	public void delete(String dni);

}
