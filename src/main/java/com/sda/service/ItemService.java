package com.sda.service;

import com.sda.model.Item;
import com.sda.model.User;

import java.util.List;

/**
 * Created by RENT on 2016-12-20.
 */
public interface ItemService {

    boolean editItem(Item item);

    boolean deleteItemById(int id);

    void saveItem(Item item);

    List<Item> listItems();

    void changeState();

}
