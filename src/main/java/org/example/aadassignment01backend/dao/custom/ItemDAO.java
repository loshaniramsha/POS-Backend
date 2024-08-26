package org.example.aadassignment01backend.dao.custom;

import org.example.aadassignment01backend.dao.CrudDAO;
import org.example.aadassignment01backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<Item> searchByName(String newItemCode) throws SQLException, ClassNotFoundException;
    boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException;
}
