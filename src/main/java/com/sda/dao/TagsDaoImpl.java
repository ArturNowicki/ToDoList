package com.sda.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sda.model.Tag;

@Repository("tagsDao")
public class TagsDaoImpl extends AbstractDao<Integer, Tag> implements TagsDao {

	@Override
	public Tag findById(int id) {
		return getByKey(id);
	}

	@Override
	public Optional<Tag> findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name.toLowerCase()));
		return Optional.ofNullable((Tag) criteria.uniqueResult());
	}

	@Override
	public void add(Tag tag) {
		persist(tag);
	}

	@Override
	public void deleteById(int id) {
		Tag tag = getByKey(id);
		delete(tag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> listAll() {
		Criteria criteria = createEntityCriteria();
		return (List<Tag>) criteria.list();
	}

}
