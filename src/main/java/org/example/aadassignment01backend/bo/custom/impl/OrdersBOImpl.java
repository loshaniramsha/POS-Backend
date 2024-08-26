package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.OrdersBO;
import org.example.aadassignment01backend.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextOrderId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrdersDTO search(String orderId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrdersDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
