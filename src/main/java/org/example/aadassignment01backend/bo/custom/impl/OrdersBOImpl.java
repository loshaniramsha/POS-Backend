package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.OrdersBO;
import org.example.aadassignment01backend.dao.DAOFactory;
import org.example.aadassignment01backend.dao.custom.OrdersDAO;
import org.example.aadassignment01backend.dto.OrdersDTO;
import org.example.aadassignment01backend.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO= (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders=ordersDAO.getAll();
        ArrayList<OrdersDTO> ordersDTOS=new ArrayList<>();

        for (Orders orders:allOrders){
            ordersDTOS.add(new OrdersDTO(orders.getOrderId(),orders.getDate(),orders.getDiscount(),orders.getTotal(),orders.getCustomerId()));

        }
        return ordersDTOS;
    }

    @Override
    public boolean existOrder(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.exist(id);
    }

    @Override
    public boolean saveOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.save(new Orders(dto.getOrderId(),dto.getDate(),dto.getDiscount(),dto.getTotal(),dto.getCustomerId()));
    }

    @Override
    public boolean updateOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.update(new Orders(dto.getOrderId(),dto.getDate(),dto.getDiscount(),dto.getTotal(),dto.getCustomerId()));
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.delete(id);
    }

    @Override
    public String nextOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.nextId();
    }

    @Override
    public OrdersDTO search(String orderId) throws SQLException, ClassNotFoundException {
        Orders search=ordersDAO.search(orderId);
        return new OrdersDTO(search.getOrderId(),search.getDate(),search.getDiscount(),search.getTotal(),search.getCustomerId());
    }

    @Override
    public ArrayList<OrdersDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Orders> search=ordersDAO.searchByOrderId(id);
        ArrayList<OrdersDTO> ordersDTOS=new ArrayList<>();
        for (Orders orders:search){
            ordersDTOS.add(new OrdersDTO(orders.getOrderId(),orders.getDate(),orders.getDiscount(),orders.getTotal(),orders.getCustomerId()));
        }
        return ordersDTOS;
    }
}
