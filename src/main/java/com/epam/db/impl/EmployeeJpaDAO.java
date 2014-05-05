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

	private static final String FIND_EMPLOYEES = "SELECT e FROM Employee e"
			+ " LEFT JOIN FETCH e.address a"
			+ " LEFT JOIN FETCH a.city city LEFT JOIN FETCH city.country country"
			+ " ORDER BY e.id";
	private static final String COUNT_RECORDS="SELECT COUNT(*) FROM Employee e";

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Employee> getList(int begin, int numberOfElements) {
		Query query = em.createQuery(FIND_EMPLOYEES);
		query.setFirstResult(begin);
		query.setMaxResults(numberOfElements);
		return query.getResultList();
	}

	@Override
	public int countRecords() {
		Query query = em.createQuery(COUNT_RECORDS);
		return ((Long)query.getSingleResult()).intValue();
	}

}