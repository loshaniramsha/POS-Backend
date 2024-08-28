package org.example.aadassignment01backend.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.example.aadassignment01backend.dao.CrudDAO;
import org.example.aadassignment01backend.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<Orders> {
    ArrayList<Orders> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
