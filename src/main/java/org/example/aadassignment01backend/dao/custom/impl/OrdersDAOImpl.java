package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.SQLUtil;
import org.example.aadassignment01backend.dao.custom.OrdersDAO;
import org.example.aadassignment01backend.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {


    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Orders> getAllOrders=new ArrayList<>();
        while (rst.next()){
            Orders orders=new Orders(rst.getString("id"),rst.getString("date"),rst.getDouble("discount"),rst.getDouble("total"),rst.getString("customerId"));
            getAllOrders.add(orders);
        }
        return getAllOrders;
    }

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders  VALUES(?,?,?,?,?)",dto.getOrderId(),dto.getDate(),dto.getDiscount(),dto.getTotal(),dto.getCustomerId());
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
        ResultSet rst=SQLUtil.execute("SELECT id FROM orders WHERE id=?",id);
        return rst.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1;");
        return  rst.next() ? String.format("O00-%03d",Integer.parseInt(rst.getString(1).replace("OID-",""))+1) : "OID-001";
    }

    @Override
    public Orders search(String newValue) throws SQLException, ClassNotFoundException {
       ResultSet rst=SQLUtil.execute("SELECT * FROM orders WHERE id=?",newValue+"");
       rst.next();
       Orders orders=new Orders(rst.getString("id"),rst.getString("date"),rst.getDouble("discount"),rst.getDouble("total"),rst.getString("customerId"));
       return orders;
    }

    @Override
    public ArrayList<Orders> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM orders WHERE id like ?",id+"%");
        ArrayList<Orders> orders=new ArrayList<>();
        while (rst.next()){
            orders.add(new Orders(rst.getString("id"),rst.getString("date"),rst.getDouble("discount"),rst.getDouble("total"),rst.getString("customerId")));
        }
        return orders;
    }
}
