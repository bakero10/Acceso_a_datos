package com.fisioEspacio.Proyecto.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisioEspacio.Proyecto.models.entity.Doctor;

@Repository
public class DoctorDaoImpl implements IDoctorDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Doctor").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor findOne(String dni) {
		return em.find(Doctor.class, dni);
	}

	@Override
	@Transactional
	public void save(Doctor doctor) {
		em.merge(doctor);
	}

	@Override
	@Transactional
	public void delete(String dni) {
		em.remove(findOne(dni));
	}

}
