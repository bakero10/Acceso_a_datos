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

import com.dam.springboot.app.models.dao.IPacienteDao;
import com.dam.springboot.app.models.entity.Paciente;

@Controller
@SessionAttributes("cliente")
public class PacienteController {

	@Autowired
	private IPacienteDao pacienteDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de pacientes");
		model.addAttribute("paciente", pacienteDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Paciente paciente = new Paciente();
		model.put("paciente", paciente);
		model.put("titulo", "Formulario de Paciente");
		return "form";
	}
	
	@RequestMapping(value="/form/{dni}")
	public String editar(@PathVariable(value="dni") String dni, Map<String, Object> model) {
		
		Paciente paciente = null;
		
		// if(dni > 0) {
		//	paciente = pacienteDao.findOne(dni);
		// } else {
			return "redirect:/listar";
		//}
	//	model.put("paciente", paciente);
	//	model.put("titulo", "Editar Paciente");
	//	return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Paciente paciente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de paciente");
			return "form";
		}
		
		pacienteDao.save(paciente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="dni") String dni) {
		
		//if(id > 0) {
			pacienteDao.delete(dni);
		//}
		return "redirect:/listar";
	}
}