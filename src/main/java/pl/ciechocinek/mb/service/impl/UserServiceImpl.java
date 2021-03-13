package pl.ciechocinek.mb.service.impl;

import java.util.List;

import pl.ciechocinek.mb.dao.UserDao;
import pl.ciechocinek.mb.dao.impl.UserDaoImpl;
import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.service.UserService;

public class UserServiceImpl implements UserService {
	private static UserService userServiceImpl;
	private UserDao userDao;

	private UserServiceImpl() {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserService getUserService() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}

	@Override
	public User create(User t) {
		return userDao.create(t);
	}

	@Override
	public User read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public User update(User t, Integer id) {
		return userDao.update(t, id);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);

	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.readByEmail(email);
		// return readAll().stream().filter(user ->
		// user.getEmail().equals(email)).findFirst().orElse(null);
	}

}
