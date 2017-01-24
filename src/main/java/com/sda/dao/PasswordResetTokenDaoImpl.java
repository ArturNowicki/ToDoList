package com.sda.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sda.model.PasswordResetToken;

@Repository("passwordResetTokenDao")
public class PasswordResetTokenDaoImpl extends AbstractDao<Integer, PasswordResetToken> implements PasswordResetTokenDao {

	@Override
	public Optional<PasswordResetToken> findByToken(String token) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eqOrIsNull("token", token));
		return Optional.ofNullable((PasswordResetToken) criteria.uniqueResult());
	}

	@Override
	public void save(PasswordResetToken token) {
		persist(token);
	}
	
	@Override
	public void deleteById(int id) {
		PasswordResetToken token = getByKey(id);
		delete(token);
	}

}
