package com.fisioEspacio.Proyecto.models.dao;

import java.util.List;
import java.util.Optional;

import com.fisioEspacio.Proyecto.models.entity.Paciente;

public interface IPacienteDao {
	
	public List<Paciente> findAll();

	public void save(Paciente paciente);
	
	public Paciente findOne(String dni);
	
	public void delete(String dni);

}
