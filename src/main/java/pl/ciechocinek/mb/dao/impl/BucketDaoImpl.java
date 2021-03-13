package pl.ciechocinek.mb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.BucketDao;
import pl.ciechocinek.mb.domain.Bucket;
import pl.ciechocinek.mb.shared.FactoryManager;

public class BucketDaoImpl implements BucketDao {
	private EntityManager em = FactoryManager.getEntityManager();
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	@Override
	public Bucket create(Bucket t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			LOG.info("Add a new Bucket  " + t.toString());
		} catch (Exception e) {
			LOG.error(e);
		}
		return t;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			bucket = em.find(Bucket.class, id);
		} catch (Exception e) {
			LOG.error(e);
		}
		return bucket;
	}

	@Override
	public Bucket update(Bucket t, Integer id) {
		throw new IllegalStateException("There is no update for bucke");
	}

	@Override
	public void delete(Integer id) {
		try {
			Bucket bucket = read(id);
			em.getTransaction().begin();
			em.remove(bucket);
			em.getTransaction().commit();
			LOG.info("Delete bucket by ID " + id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bucket> readAll() {
		Query query = null;
		try {
			query = em.createQuery("SELECT e FROM Bucket e");
		} catch (Exception e) {
			LOG.error(e);
		}
		return query.getResultList();
	}

}
