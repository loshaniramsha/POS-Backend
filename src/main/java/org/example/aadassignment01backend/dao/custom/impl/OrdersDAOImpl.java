package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.custom.OrdersDAO;
import org.example.aadassignment01backend.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> searchByCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Orders search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
