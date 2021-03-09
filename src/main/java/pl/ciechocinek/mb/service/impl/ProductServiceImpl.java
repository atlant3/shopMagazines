package pl.ciechocinek.mb.service.impl;

import java.sql.SQLException;
import java.util.List;

import pl.ciechocinek.mb.dao.ProductDao;
import pl.ciechocinek.mb.dao.impl.ProductDaoImpl;
import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private static ProductService productServiceImpl;
	private ProductDao productDao;
	
	private ProductServiceImpl() {
		try {
			productDao = new ProductDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public static ProductService getProductService() {
		if (productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}
	@Override
	public Product create(Product t) {
		return productDao.create(t);
	}

	@Override
	public Product read(Integer id) {
		return productDao.read(id);
		
	}

	@Override
	public Product update(Product t, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> readAll() {
		// TODO Auto-generated method stub
		return productDao.readAll();
	}

}
