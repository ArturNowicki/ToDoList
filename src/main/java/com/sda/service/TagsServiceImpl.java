package com.sda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.persistence.dao.TagsDao;
import com.sda.persistence.model.Tag;

@Service("tagsService")
@Transactional
public class TagsServiceImpl implements TagsService {

	@Autowired
	private TagsDao dao;
	
	@Override
	public Tag findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Optional<Tag> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void save(Tag tag) {
		dao.add(tag);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<Tag> listAll() {
		return dao.listAll();
	}

	@Override
	public boolean isTagUnique(String name) {
		Optional<Tag> maybeTag = dao.findByName(name);
		if(maybeTag.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
