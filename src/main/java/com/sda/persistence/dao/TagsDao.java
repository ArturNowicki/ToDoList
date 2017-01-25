package com.sda.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.sda.persistence.model.Tag;

public interface TagsDao {

	Tag findById(int id);

	Optional<Tag> findByName(String name);

	void add(Tag tag);

	void deleteById(int id);

	List<Tag> listAll();
}
