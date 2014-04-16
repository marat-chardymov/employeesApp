package com.epam.db;

import java.util.List;

import com.epam.entities.Country;

public interface ICountryDAO {
    public void save(Country country);
    
    public List<Country> findAll();
}
