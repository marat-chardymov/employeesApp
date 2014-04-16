package com.epam.db.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.epam.db.ICountryDAO;
import com.epam.entities.Country;

@Repository
@Transactional
public class CountryDAO implements ICountryDAO{
	@Resource
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.save(country);
    }
    
    @Override
    public List<Country> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Country.class);
        return criteria.list();
    }
}
