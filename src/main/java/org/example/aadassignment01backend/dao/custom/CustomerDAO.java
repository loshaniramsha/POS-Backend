package org.example.aadassignment01backend.dao.custom;

import org.example.aadassignment01backend.dao.CrudDAO;
import org.example.aadassignment01backend.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<Customer> searchByContact(String newValue) throws SQLException, ClassNotFoundException;

}
