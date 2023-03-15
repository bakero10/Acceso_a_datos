package com.fisioEspacio.Proyecto.models.dao;

import java.util.List;

import com.fisioEspacio.Proyecto.models.entity.Doctor;

public interface IDoctorDao {
	
	public List<Doctor> findAll();

	public void save(Doctor doctor);
	
	public Doctor findOne(String dni);
	
	public void delete(String dni);

}
