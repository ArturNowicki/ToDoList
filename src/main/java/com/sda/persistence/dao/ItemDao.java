package com.sda.persistence.dao;

import java.util.List;

import com.sda.persistence.model.Item;

public interface ItemDao {

	Item findById(int id);

	void add(Item item);

	void deleteById(int id);

	List<Item> listAll();

	// List<Item> findByName(String name);
	//
	// List<Item> findByUser(User user);
}
