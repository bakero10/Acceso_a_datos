package com.dam.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dam.springboot.app.models.entity.Paciente;

@Repository
public class PacienteDaoImpl implements IPacienteDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findOne(String dni) {
		return em.find(Paciente.class, dni);
	}

	@Override
	@Transactional
	public void save(Paciente paciente) {
	//	if (paciente.getId() != null && paciente.getId() > 0) {
			em.merge(paciente);
	//	} else {
			em.persist(paciente);
		}
	//}

	@Override
	@Transactional
	public void delete(String dni) {
		em.remove(findOne(dni));
	}

}
