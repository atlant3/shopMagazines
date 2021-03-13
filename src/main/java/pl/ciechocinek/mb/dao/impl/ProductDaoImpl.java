package pl.ciechocinek.mb.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.ProductDao;
import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.shared.FactoryManager;

public class ProductDaoImpl implements ProductDao {
	private EntityManager em = FactoryManager.getEntityManager();

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	@Override
	public Product create(Product t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			LOG.info("Add a new product to DataBase = " + t.toString());
		} catch (Exception e) {
			LOG.error(e);
		}
		return t;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			product = em.find(Product.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product t, Integer id) {
		try {

		} catch (Exception e) {
		}
		return t;
	}

	@Override
	public void delete(Integer id) {
		try {
			
		} catch (Exception e) {

		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> readAll() {
		Query query = null;
		try {
			query = em.createQuery("SELECT e FROM Product e");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}

}
