package com.sda.service;

import java.util.List;

import com.sda.persistence.model.Item;

public interface ItemService {

	Item findById(int id);

	void save(Item item);

	void deleteById(int id);

	List<Item> listAll();

	void update(Item item);
	
	void itemStateBack(int itemId);

	void itemStateForward(int itemId);
}
