package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.SQLUtil;
import org.example.aadassignment01backend.dao.custom.OrderDetailsDAO;
import org.example.aadassignment01backend.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetails VALUES(?,?,?,?)",dto.getOrderId(),dto.getItemId(),dto.getQty(),dto.getUnitPrice());
    }

    @Override
    public ArrayList<OrderDetails> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM orderdetails WHERE orderId=?",id);
        ArrayList<OrderDetails> orderDetails=new ArrayList<>();

        while (rst.next()){
            orderDetails.add(new OrderDetails(rst.getString("orderId"),rst.getString("itemId"),rst.getInt("qty"),rst.getDouble("unitPrice"), rst.getDouble("total")));

        }
        return orderDetails;
    }
}
