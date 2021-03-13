package pl.ciechocinek.mb.dao;

import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User>  {
	public User readByEmail(String email);
}
