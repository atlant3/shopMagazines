package pl.ciechocinek.mb.shared;

import java.util.List;

public interface AbstractCRUD<T> {
	T create(T t);

	T read(Integer id);

	T update(T t,Integer id);

	void delete(Integer id);

	List<T> readAll();
}
