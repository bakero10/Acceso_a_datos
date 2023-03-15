package com.fisioEspacio.Proyecto.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.fisioEspacio.Proyecto.models.dao.IDoctorDao;
import com.fisioEspacio.Proyecto.models.entity.Doctor;

@Controller
public class DoctorController {

	@Autowired
	private IDoctorDao doctorDao;

	@RequestMapping(value = "/listar/doctores", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de doctores");
		model.addAttribute("doctores", doctorDao.findAll());
		return "listar_doctores";
	}
	
	@RequestMapping(value = "/form/doctor")
	public String crear(Map<String, Object> model) {

		Doctor doctor = new Doctor();
		model.put("doctor", doctor);
		model.put("titulo", "Formulario de doctor");
		return "form_doctor";
	}
	
	@GetMapping(value = "/form/doctor/editar/{dni}")
	public String editarPorId(@PathVariable(value = "dni") String dni, Map<String, Object> model) {

		Doctor doctor = null;

		if (!dni.isEmpty()) {
			doctor = doctorDao.findOne(dni);
			if (doctor == null) {
				return "listar_doctores";
			}
		} else {
			return "listar_doctores";
		}
		model.put("doctor", doctor);
		model.put("titulo", "Editar doctor con DNI " + doctor.getDni());
		return "form_doctor";
	}

	@GetMapping(value = "/form/doctor/eliminar/{dni}")
	public String eliminar(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		if (!dni.isEmpty()) {
			doctorDao.delete(dni);
		}
		return "redirect:/listar/doctores";
	}

	@PostMapping(value = "/form/doctor/guardar")
	public String guardarCursoCrear(@Valid Doctor Doctor, BindingResult result, Model model, SessionStatus status) {
		status.setComplete();
		doctorDao.save(Doctor);
		return "redirect:/listar/doctores";
	}
	
}