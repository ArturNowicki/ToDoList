package com.sda.dao;

import com.sda.model.Item;

import java.util.List;

public interface ItemDao {

    boolean deleteItemById(int id);

    void addItem(Item item);

    List<Item> listItems();

}
