package com.epam.db.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public List<Employee> getList(int begin, int numberOfElements){
		return null;
	}

    
}