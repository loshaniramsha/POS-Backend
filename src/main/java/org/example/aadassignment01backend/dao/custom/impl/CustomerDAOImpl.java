package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.SQLUtil;
import org.example.aadassignment01backend.dao.custom.CustomerDAO;
import org.example.aadassignment01backend.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> searchByContact(String newValue) throws SQLException, ClassNotFoundException {
       ResultSet rst= SQLUtil.execute("SELECT * FROM customer WHERE contact=?",newValue+"%");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            getAllCustomer.add(new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getString("contact")));

        }
        return getAllCustomer;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer customer=new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getString("contact"));
            getAllCustomer.add(customer);
        }
        return getAllCustomer;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer(id,name,address,contact) VALUES(?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getContact());

    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE customer SET name=?,address=?,contact=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getContact(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set=SQLUtil.execute("SELECT id FROM customer WHERE id=?",id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        if (rst.next()){
            String id=rst.getString("id");
            int newCustomerId=Integer.parseInt(id.replace("C00-",""))+1;
            return String.format("C00-%03d",newCustomerId);
        }
        else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
    ResultSet rst= SQLUtil.execute("SELECT * FROM customer WHERE id=?",newValue+"");
    rst.next();
    Customer customer=new Customer(newValue+"",rst.getString("name"),rst.getString("address"),rst.getString("contact"));
    return customer;
    }
}
