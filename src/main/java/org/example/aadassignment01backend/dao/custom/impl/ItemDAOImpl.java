package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.custom.ItemDAO;
import org.example.aadassignment01backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> searchByName(String newItemCode) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
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
    public Item search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
