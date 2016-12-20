package com.sda.service;

import com.sda.dao.ItemDao;
import com.sda.model.Item;
import com.sda.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao dao;

    @Override
    public boolean editItem(Item item) {
        Optional<Item> maybeItem = dao.findById(item.getId());
        if(maybeItem.isPresent()) {
            Item editedItem = maybeItem.get();
            editedItem.setLogin(item.getLogin());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItemById(int id) {
        return dao.deleteItemById(id);
    }

    @Override
    public void saveItem(Item item) {
        dao.addItem(item);
    }

    @Override
    public List<Item> listItems() {
        return dao.listItems();
    }

    @Override
    public void changeState() {
        //TODO
    }
}
