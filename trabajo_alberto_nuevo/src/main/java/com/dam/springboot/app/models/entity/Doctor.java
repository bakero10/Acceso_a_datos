package com.dam.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String dni;
	
	@NotEmpty
	private String especialidad;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	public Doctor() {}
	
	// CONSTRUCTOR
	public Doctor(String dni, @NotEmpty String especialidad, @NotEmpty String nombre, @NotEmpty String apellido) {
		super();
		this.dni = dni;
		this.especialidad = especialidad;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	
	// GETTERS AND SETTERS
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
