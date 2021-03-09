package pl.ciechocinek.mb.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.BucketDao;
import pl.ciechocinek.mb.domain.Bucket;
import pl.ciechocinek.mb.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {
	private static String READ_ALL = "select * from bucket";
	private static String CREATE = "insert into bucket(user_id, product_id, purchase_date) values(?,?,?)";
	private static String READ_BY_ID = "select * from bucket where id = ?";
	private static String DELETE_BY_ID = "delete from bucket where id=?";

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connect();
	}

	@Override
	public Bucket create(Bucket t) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, t.getUserId());
			preparedStatement.setInt(2, t.getProductId());
			preparedStatement.setDate(3, new Date(t.getPurchaseDate().getTime()));
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			t.setId(rs.getInt(1));
			LOG.info("Add a new Bucket  " + t.getUserId().toString());
		} catch (SQLException e) {
			LOG.error(e);
		}
		return t;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			Integer bucketId = result.getInt("id");
			Integer userId = result.getInt("user_id");
			Integer productId = result.getInt("product_id");
			Date purchase_date = result.getDate("purchase_date");
			bucket = new Bucket(bucketId, userId, productId, purchase_date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			LOG.info("Delete bucket by ID " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}

	}

	@Override
	public List<Bucket> readAll() {
		List<Bucket> listOfBuckets = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer bucketId = result.getInt("id");
				Integer userId = result.getInt("user_id");
				Integer productId = result.getInt("product_id");
				Date purchase_date = result.getDate("purchase_date");
				listOfBuckets.add(new Bucket(bucketId, userId, productId, purchase_date));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

		return listOfBuckets;
	}

}
