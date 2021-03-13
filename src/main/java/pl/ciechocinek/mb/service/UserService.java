package pl.ciechocinek.mb.service;

import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
	public User getUserByEmail(String email);
}
