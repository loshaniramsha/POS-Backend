package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.OrderDetailsBO;
import org.example.aadassignment01backend.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    @Override
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailsDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
