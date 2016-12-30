package com.sda.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.dao.ItemDao;
import com.sda.model.Item;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDao dao;

	@Override
	public Item findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void save(Item item) {
		dao.add(item);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Item> listAll() {
		return dao.listAll();
	}

	@Override
	public void update(Item item) {
		Item entity = dao.findById(item.getId());
		if(null != entity) {
			entity.setAssignedUser(item.getAssignedUser());
			entity.setBody(item.getBody());
			entity.setCompletedHours(item.getCompletedHours());
			entity.setModified(Date.valueOf(LocalDate.now()));
			entity.setPriority(item.getPriority());
			entity.setRemainingHours(item.getRemainingHours());
			entity.setSeverity(item.getSeverity());
//			entity.setState(item.getState());
			entity.setTags(item.getTags());
		}
	}

}
