package com.epam.db.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Repository
@Transactional
public class EmployeeDAO implements IEmployeeDAO {

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Employee> getFirst100List() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from Employee e join fetch e.address "
						+"left join fetch e.workplaces workplace");
			query.setMaxResults(100);
			return query.list();
	}

	@Override
	public void save(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public Employee read(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Employee) session.get(Employee.class, new Integer(id));
	}

}
