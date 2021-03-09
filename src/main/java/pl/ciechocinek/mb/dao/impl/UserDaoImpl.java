package pl.ciechocinek.mb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.UserDao;
import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {
	private String generatedColumns[] = { "id" };
	private static String READ_ALL = "select * from user";
	private static String CREATE = "insert into user( last_name, first_name, email, role, password ) values(?,?,?,?,?)";
	private static String READ_BY_ID = "select * from user where id = ?";
	private static String READ_BY_EMAIL = "select * from user where email = ?";
	private static String UPDATE_BY_ID = "update user set last_name=?, first_name=?,email=?,password=?,role=? where id=?";
	private static String DELETE_BY_ID = "delete from user where id=?";

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connect();
	}

	@Override
	public User create(User t) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, generatedColumns);
			preparedStatement.setString(1, t.getLastName());
			preparedStatement.setString(2, t.getFirstName());
			preparedStatement.setString(3, t.getEmail());
			preparedStatement.setString(4, t.getRole());
			preparedStatement.setString(5, t.getPassword());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			t.setId(rs.getInt(1));
			System.out.print(t.toString());
			LOG.info("Add a new user " + t.getFirstName().toString());

		} catch (SQLException e) {
			LOG.error(e);
		}
		return t;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			Integer userId = result.getInt("id");
			String lastName = result.getString("last_name");
			String firstName = result.getString("first_name");
			String email = result.getString("email");
			String password = result.getString("password");
			String role = result.getString("role");
			user = new User(userId, lastName, firstName, email, password, role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		return user;

	}
	@Override
	public User readByEmail(String email) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			Integer userId = result.getInt("id");
			String lastName = result.getString("last_name");
			String firstName = result.getString("first_name");
			String emailUser = result.getString("email");
			String password = result.getString("password");
			String role = result.getString("role");
			user = new User(userId, lastName, firstName, emailUser, password, role);
			LOG.info("Read by Email " + email );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		return user;

	}

	@Override
	public User update(User t, Integer id) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setInt(6, id);
			preparedStatement.setString(1, t.getLastName());
			preparedStatement.setString(2, t.getFirstName());
			preparedStatement.setString(3, t.getEmail());
			preparedStatement.setString(4, t.getRole());
			preparedStatement.setString(5, t.getPassword());
			preparedStatement.executeUpdate();
			LOG.info("Update user id " + id);
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
			LOG.info("Delete user id " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}

	}

	@Override
	public List<User> readAll() {
		List<User> listOfUsers = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer userId = result.getInt("id");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String password = result.getString("password");
				String role = result.getString("role");
				listOfUsers.add(new User(userId, lastName, firstName, email, password, role));
			}
			for (User user : listOfUsers) {
				System.out.println(user.toString());

			}
		} catch (SQLException e) {
			LOG.error(e);
		}

		return listOfUsers;
	}

}
