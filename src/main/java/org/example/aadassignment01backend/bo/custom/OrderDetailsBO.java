package org.example.aadassignment01backend.bo.custom;

import org.example.aadassignment01backend.bo.SuperBO;
import org.example.aadassignment01backend.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailsDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
