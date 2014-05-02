package com.epam.db.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Repository
@Transactional
public class EmployeeJpaDAO implements IEmployeeDAO {

	private static final String FIND_All_SQL = "SELECT e FROM Employee e LEFT JOIN FETCH e.address a "
			+ "LEFT JOIN FETCH a.city city LEFT JOIN FETCH city.country country ORDER BY e.id";

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