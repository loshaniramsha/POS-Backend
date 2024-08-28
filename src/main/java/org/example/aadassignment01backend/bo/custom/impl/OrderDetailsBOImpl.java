package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.OrderDetailsBO;
import org.example.aadassignment01backend.dao.DAOFactory;
import org.example.aadassignment01backend.dao.custom.OrderDetailsDAO;
import org.example.aadassignment01backend.dto.OrderDetailsDTO;
import org.example.aadassignment01backend.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetails(dto.getOrderId(),dto.getItemId(),dto.getQty(),dto.getUnitPrice(),dto.getTotal()));
    }

    @Override
    public ArrayList<OrderDetailsDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
       ArrayList<OrderDetails> search=orderDetailsDAO.searchByOrderId(id);
       ArrayList<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();
       for (OrderDetails orderDetails:search){
           orderDetailsDTOS.add(new OrderDetailsDTO(orderDetails.getOrderId(),orderDetails.getItemId(),orderDetails.getQty(),orderDetails.getUnitPrice(),orderDetails.getTotal()));
       }
       return orderDetailsDTOS;
    }
}
