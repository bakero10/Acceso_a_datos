package com.fisioEspacio.Proyecto.models.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisioEspacio.Proyecto.models.entity.Paciente;

@Repository
public class PacienteDaoImpl implements IPacienteDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Paciente").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findOne(String dni) {
		return em.find(Paciente.class, dni);
	}

	@Override
	@Transactional
	public void save(Paciente paciente) {
		em.merge(paciente);
	}

	@Override
	@Transactional
	public void delete(String dni) {
		em.remove(findOne(dni));
	}

}
