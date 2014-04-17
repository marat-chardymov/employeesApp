package com.epam.db.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.epam.db.ICityDAO;
import com.epam.entities.City;

public class CityDAO implements ICityDAO{
	@Resource
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.save(city);
    }
}
