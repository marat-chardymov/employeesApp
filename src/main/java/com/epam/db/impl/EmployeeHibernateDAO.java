package com.epam.db.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.epam.db.IEmployeeDAO;
import com.epam.entities.Employee;

@Repository
@Transactional
public class EmployeeHibernateDAO implements IEmployeeDAO {

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Employee> getList(int begin, int numberOfElements) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(begin);
		criteria.setMaxResults(numberOfElements);
		return criteria.list();
	}

	@Override
	public int countRecords() {
		Session session = sessionFactory.getCurrentSession();
		return ((Long) session.getNamedQuery("COUNT_RECORDS").uniqueResult())
				.intValue();
	}

}
