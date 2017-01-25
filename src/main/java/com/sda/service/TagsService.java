package com.sda.service;

import java.util.List;
import java.util.Optional;

import com.sda.persistence.model.Tag;

public interface TagsService {

    Tag findById(int id);
    
    Optional<Tag> findByName(String name);

    void save(Tag tag);

    void deleteById(int id);

    List<Tag> listAll();
    
	boolean isTagUnique(String name);

}
