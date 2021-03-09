package pl.ciechocinek.mb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.ProductDao;
import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {
	private String generatedColumns[] = { "id" };
	private static String READ_ALL = "select * from product";
	private static String CREATE = "insert into product( name, description, price ) values(?,?,?)";
	private static String READ_BY_ID = "select * from product where id = ?";
	private static String UPDATE_BY_ID = "update product set name=?, description=?,price=? where id=?";
	private static String DELETE_BY_ID = "delete from product where id=?";

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public ProductDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connect();
	}

	@Override
	public Product create(Product t) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, generatedColumns);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getDescription());
			preparedStatement.setDouble(3, t.getPrice());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			t.setId(rs.getInt(1));
			LOG.info("Add a new Product " + t.getName().toString());
		} catch (SQLException e) {
			LOG.error(e);
		}
		return t;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			Integer productId = result.getInt("id");
			String name = result.getString("name");
			String description = result.getString("description");
			float price = result.getFloat("price");
			product = new Product(productId, name, description, price);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product t, Integer id) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setInt(4, id);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getDescription());
			preparedStatement.setDouble(3, t.getPrice());
			preparedStatement.executeUpdate();
			LOG.info("update Product by ID " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		return t;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			LOG.info("delete Product by ID " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}

	}

	@Override
	public List<Product> readAll() {
		List<Product> listOfProducts = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer productId = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				float price = result.getFloat("price");
				listOfProducts.add(new Product(productId, name, description, price));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

		return listOfProducts;
	}

}
