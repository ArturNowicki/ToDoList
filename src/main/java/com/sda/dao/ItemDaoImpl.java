package com.sda.dao;

import com.sda.model.Item;
import com.sda.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {

    @Override
    public void addItem(Item item) {
        persist(item);
    }

    @Override
    public boolean deleteItemById(int id) {
        Optional<Item> maybeItem = getByKey(id);
        if (maybeItem.isPresent()) {
            delete(maybeItem.get());
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> listItems() {
        Criteria criteria = createEntityCriteria();
        return (List<Item>) criteria.list();
    }

    @Override
    public boolean editItem(Item item) {
        // TODO

        // edit title

        // edit body

        // edit state

        // edit tags

        // modfified

        // edit remaining time

        // edit completed time

        return false;
    }

    @Override
    public void changeState() {
        //TODO
    }
}
