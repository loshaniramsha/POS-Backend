package org.example.aadassignment01backend.bo.custom.impl;

import org.example.aadassignment01backend.bo.custom.ItemBO;
import org.example.aadassignment01backend.dao.DAOFactory;
import org.example.aadassignment01backend.dao.custom.ItemDAO;
import org.example.aadassignment01backend.dto.ItemDTO;
import org.example.aadassignment01backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();

        for (Item item:allItem){
            itemDTOS.add(new ItemDTO(item.getItemId(),item.getItemName(),item.getItemPrice(),item.getItemQty()));
        }
        return itemDTOS;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemId(),dto.getItemName(),dto.getItemPrice(),dto.getItemQty()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
       return itemDAO.update(new Item(dto.getItemId(),dto.getItemName(),dto.getItemPrice(),dto.getItemQty()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String nextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.nextId();
    }

    @Override
    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(newItemCode);
        return new ItemDTO(item.getItemId(),item.getItemName(),item.getItemPrice(),item.getItemQty());
    }

    @Override
    public ArrayList<ItemDTO> searchByName(String newItemCode) throws SQLException, ClassNotFoundException {
        ArrayList<Item> allitem=itemDAO.searchByName(newItemCode);
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:allitem){
            itemDTOS.add(new ItemDTO(item.getItemId(),item.getItemName(),item.getItemPrice(),item.getItemQty()));
        }
        return itemDTOS;
    }

    @Override
    public boolean updateQty(String id, String qty) throws SQLException, ClassNotFoundException {
      return itemDAO.updateQty(id,qty);
    }
}
