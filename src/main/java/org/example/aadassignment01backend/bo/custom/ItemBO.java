package org.example.aadassignment01backend.bo.custom;

import org.example.aadassignment01backend.bo.SuperBO;
import org.example.aadassignment01backend.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    boolean existItem(String id) throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    String nextItemId() throws SQLException, ClassNotFoundException;
    ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> searchByName(String newItemCode) throws SQLException, ClassNotFoundException;
    boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException;
}
