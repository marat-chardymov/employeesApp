package com.epam.db.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Repository
@Transactional
public class EmployeeJpaDAO implements IEmployeeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Employee> getList(int begin, int numberOfElements) {
		Query query = em.createNamedQuery("FIND_EMPLOYEES");
		query.setFirstResult(begin);
		query.setMaxResults(numberOfElements);
		return query.getResultList();
	}

	@Override
	public int countRecords() {
		Query query = em.createNamedQuery("COUNT_RECORDS");
		return ((Long) query.getSingleResult()).intValue();
	}

}