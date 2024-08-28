package org.example.aadassignment01backend.dao.custom;

import org.example.aadassignment01backend.dao.CrudDAO;
import org.example.aadassignment01backend.dao.SuperDAO;
import org.example.aadassignment01backend.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends SuperDAO {
    boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetails> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
