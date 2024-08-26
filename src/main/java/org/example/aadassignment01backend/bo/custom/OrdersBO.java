package org.example.aadassignment01backend.bo.custom;

import org.example.aadassignment01backend.bo.SuperBO;
import org.example.aadassignment01backend.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    boolean existOrder(String id) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    String nextOrderId() throws SQLException, ClassNotFoundException;
    OrdersDTO search(String orderId) throws SQLException, ClassNotFoundException;
    ArrayList<OrdersDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
