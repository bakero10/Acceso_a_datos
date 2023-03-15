package com.dam.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.dam.springboot.app.models.dao.IDoctorDao;
import com.dam.springboot.app.models.dao.IPacienteDao;
import com.dam.springboot.app.models.entity.Doctor;
import com.dam.springboot.app.models.entity.Paciente;

@Controller
@SessionAttributes("cliente")
public class DoctorController2 {

	@Autowired
	private IDoctorDao doctorDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de pacientes");
		model.addAttribute("paciente", doctorDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Doctor doctor = new Doctor();
		model.put("paciente", doctor);
		model.put("titulo", "Formulario de Paciente");
		return "form";
	}
	
	@RequestMapping(value="/form/{dni}")
	public String editar(@PathVariable(value="dni") String dni, Map<String, Object> model) {
		
		Doctor doctor = null;
		
		// if(dni > 0) {
		//	doctor = doctorDao.findOne(dni);
		// } else {
			return "redirect:/listar";
		//}
	//	model.put("doctor", doctor);
	//	model.put("titulo", "Editar Doctor");
	//	return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Doctor doctor, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de doctor");
			return "form";
		}
		
		doctorDao.save(doctor);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{dni}")
	public String eliminar(@PathVariable(value="dni") String dni) {
		
		//if(id > 0) {
		doctorDao.delete(dni);
		//}
		return "redirect:/listar";
	}
}