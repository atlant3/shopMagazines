package pl.ciechocinek.mb.service.impl;

import java.util.List;

import pl.ciechocinek.mb.dao.BucketDao;

import pl.ciechocinek.mb.dao.impl.BucketDaoImpl;

import pl.ciechocinek.mb.domain.Bucket;
import pl.ciechocinek.mb.service.BucketService;

public class BucketServiceImpl implements BucketService {
	private static BucketServiceImpl bucketServiceImpl;
	private BucketDao bucketDao;

	private BucketServiceImpl() {
		try {
			bucketDao = new BucketDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BucketService getBucketService() {
		if (bucketServiceImpl == null) {
			bucketServiceImpl = new BucketServiceImpl();
		}
		return bucketServiceImpl;
	}

	@Override
	public Bucket create(Bucket t) {
		return bucketDao.create(t);
	}

	@Override
	public Bucket read(Integer id) {
		return bucketDao.read(id);
	}

	@Override
	public Bucket update(Bucket t, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		bucketDao.delete(id);
	}

	@Override
	public List<Bucket> readAll() {
		return bucketDao.readAll();
	}

}
