package com.dam.springboot.app.models.dao;

import java.util.List;

import com.dam.springboot.app.models.entity.Paciente;

public interface IPacienteDao {
	
	public List<Paciente> findAll();

	public void save(Paciente paciente);
	
	public Paciente findOne(String dni);
	
	public void delete(String dni);

}
