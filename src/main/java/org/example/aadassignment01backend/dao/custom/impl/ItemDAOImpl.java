package org.example.aadassignment01backend.dao.custom.impl;

import org.example.aadassignment01backend.dao.SQLUtil;
import org.example.aadassignment01backend.dao.custom.ItemDAO;
import org.example.aadassignment01backend.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> searchByName(String newItemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM item WHERE name LIKE ?", "%" + newItemCode + "%");
        ArrayList<Item> getAllItem=new ArrayList<>();
        while (rst.next()){
            getAllItem.add(new Item(rst.getString("itemId"),rst.getString("itemName"),rst.getString("itemPrice"),rst.getString("itemQty")));
        }
        return getAllItem;
    }

    @Override
    public boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET itemQty=? WHERE id=?",qty,id);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst=SQLUtil.execute("SELECT * FROM item");
       ArrayList<Item> getAllItem=new ArrayList<>();
       while (rst.next()){
           Item item=new Item(rst.getString("itemId"),rst.getString("itemName"),rst.getString("itemPrice"),rst.getString("itemQty"));
           getAllItem.add(item);
       }
       return getAllItem;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item(itemId,itemName,itemPrice,itemQty) VALUES(?,?,?,?)",dto.getItemId(),dto.getItemName(),dto.getItemPrice(),dto.getItemQty());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET itemName=?,itemPrice=?,itemQty=? WHERE itemId=?",dto.getItemName(),dto.getItemPrice(),dto.getItemQty(),dto.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE itemId=?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       ResultSet rst=SQLUtil.execute("SELECT itemId FROM item WHERE itemId=?",id);
       return rst.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1;");
        if (rst.next()){
            String id=rst.getString("itemId");
            int newItemId=Integer.parseInt(id.replace("I-",""))+1;
            return String.format("I-%03d",newItemId);
        }
        else {
            return "I-001";
        }
    }

    @Override
    public Item search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM item WHERE itemId=?",newValue+"");
        rst.next();
        Item item=new Item(newValue+"",rst.getString("itemName"),rst.getString("itemPrice"),rst.getString("itemQty"));
        return item;
    }
}
