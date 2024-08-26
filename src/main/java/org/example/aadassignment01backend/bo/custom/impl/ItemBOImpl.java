package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.ItemBO;
import org.example.aadassignment01backend.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextItemId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> searchByName(String newItemCode) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException {
        return false;
    }
}
