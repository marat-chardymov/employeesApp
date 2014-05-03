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

	private static final String FIND_All_SQL = "SELECT e FROM Employee e"
			+ " LEFT JOIN FETCH e.address a"
			+ " LEFT JOIN FETCH a.city city LEFT JOIN FETCH city.country country"
//			+ " LEFT JOIN FETCH e.workplaces workplace"
//			+ " LEFT JOIN FETCH workplace.position position"
//			+ " LEFT JOIN FETCH workplace.office office"
//			+ " LEFT JOIN FETCH office.company company"
//			+ " LEFT JOIN FETCH office.address office_address"
//			+ " LEFT JOIN FETCH office_address.city office_city"
//			+ " LEFT JOIN FETCH office_city.country office_country"
			+ " ORDER BY e.id";

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Employee> getList(int begin, int numberOfElements) {
		Query query = em.createQuery(FIND_All_SQL);
		query.setFirstResult(begin);
		query.setMaxResults(numberOfElements);
		return query.getResultList();

	}

}