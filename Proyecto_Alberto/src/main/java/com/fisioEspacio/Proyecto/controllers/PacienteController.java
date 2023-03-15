package com.fisioEspacio.Proyecto.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.fisioEspacio.Proyecto.models.dao.DoctorDaoImpl;
import com.fisioEspacio.Proyecto.models.dao.IPacienteDao;
import com.fisioEspacio.Proyecto.models.entity.Doctor;
import com.fisioEspacio.Proyecto.models.entity.Paciente;

@Controller
public class PacienteController {

	@Autowired
	private IPacienteDao pacienteDao;
	
	@Autowired
	private DoctorDaoImpl doctorDao;

	@RequestMapping(value = "/listar/pacientes", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Paciente> lista = pacienteDao.findAll();
		System.out.println(lista.size());
		model.addAttribute("titulo", "Listado de pacientes");
		model.addAttribute("pacientes", pacienteDao.findAll());
		return "listar_pacientes";
	}

	@RequestMapping(value = "/form/paciente")
	public String crear(Map<String, Object> model) {

		Paciente paciente = new Paciente();
		model.put("paciente", paciente);
		model.put("titulo", "Formulario de Paciente");
		return "form_paciente";
	}

	@GetMapping(value = "/form/paciente/editar/{dni}")
	public String editarPorId(@PathVariable(value = "dni") String dni, Map<String, Object> model) {

		Paciente paciente = null;

		if (!dni.isEmpty()) {
			paciente = pacienteDao.findOne(dni);
			if (paciente == null) {
				return "listar_pacientes";
			}
		} else {
			return "listar_pacientes";
		}
		model.put("paciente", paciente);
		model.put("titulo", "Editar paciente con DNI " + paciente.getDni());
		return "form_paciente";
	}

	@GetMapping(value = "/form/paciente/eliminar/{dni}")
	public String eliminar(@PathVariable(value = "dni") String dni, Map<String, Object> model) {
		if (!dni.isEmpty()) {
			pacienteDao.delete(dni);
		}
		return "redirect:/listar/pacientes";
	}

	@PostMapping(value = "/form/paciente/guardar")
	public String guardarCursoCrear(@Valid Paciente paciente, BindingResult result, Model model, SessionStatus status) {
		status.setComplete();
		
		List<Doctor> listaDoctores = doctorDao.findAll();
		
		int doctorRandomNumero = new Random().nextInt(listaDoctores.size());
		
		paciente.setDoctor(listaDoctores.get(doctorRandomNumero));
		pacienteDao.save(paciente);
		return "redirect:/listar/pacientes";
	}

}