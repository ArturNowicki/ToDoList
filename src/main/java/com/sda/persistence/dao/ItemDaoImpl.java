package com.sda.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.sda.persistence.model.Item;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {

	@Override
	public Item findById(int id) {
		return getByKey(id);
	}

	@Override
	public void add(Item item) {
		persist(item);
	}

	@Override
	public void deleteById(int id) {
		Item item = getByKey(id);
		delete(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listAll() {
		Criteria criteria = createEntityCriteria();
		List<Item> result = criteria.list();
		return result.stream().distinct().collect(Collectors.toList());
		
	}


}
