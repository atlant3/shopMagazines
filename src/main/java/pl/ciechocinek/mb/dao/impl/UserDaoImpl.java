package pl.ciechocinek.mb.dao.impl;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.UserDao;
import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.shared.FactoryManager;

public class UserDaoImpl implements UserDao {
	private EntityManager em = FactoryManager.getEntityManager();

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	@Override
	public User create(User t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			LOG.info("Add a new user to dataBase" + t.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}

		return t;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			user = em.find(User.class, id);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}

		return user;

	}

	@Override
	public User readByEmail(String email) {
		User user = null;
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> from = criteria.from(User.class);
			criteria.select(from);
			criteria.where(builder.equal(from.get("email"), email));
			TypedQuery<User> typed = em.createQuery(criteria);
			user = typed.getSingleResult();
			LOG.info("Login " + user.toString());

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}

		return user;

	}

	@Override
	public User update(User t, Integer id) {
		try {
			// TODO: to be implemented
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}

		return t;
	}

	@Override
	public void delete(Integer id) {
		try {
			// TODO: to be implemented
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}

	}

	@Override
	public List<User> readAll() {
		List<User> listOfUsers = new ArrayList<>();
		try {
			// TODO: to be implemented
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
		return listOfUsers;
	}

}
